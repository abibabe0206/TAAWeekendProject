import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthUserProfile } from 'src/app/model/user-profile';
import { SharedService } from 'src/app/services/shared.service';
import { TokenStorageService } from 'src/app/authentication/token-storage.service';

@Component({
  selector: 'app-view-user-profile',
  templateUrl: './view-user-profile.component.html',
  styleUrls: ['./view-user-profile.component.css']
})
export class ViewUserProfileComponent implements OnInit {

  profileDetails: AuthUserProfile;
  info: any = {};


  constructor(
    private token: TokenStorageService,
    private sharedService: SharedService) {
      this.info = {
        token: this.token.getToken(),
        username: this.token.getUsername(),
        authorities: this.token.getAuthorities(),
      };
     }

  ngOnInit() {
    this.fetchProfileDetials();
  }

  fetchProfileDetials() {
    this.sharedService.getUserProfile(this.info.username)
    .subscribe((data: AuthUserProfile) => {
      this.profileDetails = data;
     // console.log('profileDetails', this.profileDetails);
    });
  }

}
