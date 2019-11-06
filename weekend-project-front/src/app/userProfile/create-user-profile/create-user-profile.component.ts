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

  private userProfileInfo: AuthUserProfile;

  constructor(
    private authService: AuthenticateService,
    private tokenStorage: TokenStorageService,
    private router: Router
  ) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isUserProfileFilled = true;
      this.username = this.tokenStorage.getUsername();
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

    this.authService.userProfile(this.userProfileInfo).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUsername(data.username);
        this.tokenStorage.saveAuthorities(data.authorities);

        this.isUserProfileFilledFailed = false;
        this.isUserProfileFilled = true;
        this.username = this.tokenStorage.getUsername();
       // this.router.navigate(['/user']);
        this.reloadPage();
      },
      error => {
        console.log('Login Error: ', error);
        this.errorMessage = error.error.message;
        this.isUserProfileFilledFailed = true;
      }
    );

  }

  reloadPage() {
    window.location.reload();
  }

}
