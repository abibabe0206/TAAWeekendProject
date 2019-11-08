import { Component, OnInit } from '@angular/core';

import { AuthenticateService } from '../../authentication/authenticate.service';
import { TokenStorageService } from '../../authentication/token-storage.service';
import { AuthUserProfile } from '../../model/user-profile';
import { Router } from '@angular/router';


@Component({
  selector: 'app-create-user-profile',
  templateUrl: './create-user-profile.component.html',
  styleUrls: ['./create-user-profile.component.css']
})
export class CreateUserProfileComponent implements OnInit {

  form: any = {};
  isUserProfileFilled = false;
  isUserProfileFilledFailed = false;
  errorMessage = '';
  username: string;
  roles: string[] = [];
  token: string;

  private userProfileInfo: AuthUserProfile;

  constructor(
    private authService: AuthenticateService,
    private tokenStorage: TokenStorageService,
    private router: Router
  ) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isUserProfileFilled = true;
      this.roles = this.tokenStorage.getAuthorities();
      this.username = this.tokenStorage.getUsername();
      this.token = this.tokenStorage.getToken();
    }
  }

  onSubmit() {
    console.log('UserProfile Form', this.form);

    this.userProfileInfo = new AuthUserProfile(
      this.form.userRegion,
      this.form.userDepartment,
      this.form.userVille,
      this.form.userSport,
      this.form.userPet,
      this.form.userFood
    );

    this.authService.userProfile(this.username, this.userProfileInfo).subscribe(
      data => {
        let role: string[] = [];
        role = this.tokenStorage.getAuthorities();
        this.tokenStorage.saveToken(this.token);
        this.tokenStorage.saveUsername(this.username);
        // this.tokenStorage.saveAuthorities(this.roles);

        this.isUserProfileFilledFailed = false;
        this.isUserProfileFilled = true;
        this.roles = this.tokenStorage.getAuthorities();
        this.username = this.tokenStorage.getUsername();
        this.reloadPage();
      },
      error => {
        console.log('Profile Creation Error: ', error);
        this.errorMessage = error.error.message;
        this.isUserProfileFilledFailed = true;
      }
    );

  }

  reloadPage() {
    window.location.reload();
  }

}
