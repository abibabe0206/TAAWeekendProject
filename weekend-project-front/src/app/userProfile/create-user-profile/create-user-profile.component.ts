import { Component, OnInit } from '@angular/core';

import { AuthenticateService } from '../../authentication/authenticate.service';
import { TokenStorageService } from '../../authentication/token-storage.service';
import { AuthUserProfile } from '../../model/user-profile';
import { Router } from '@angular/router';
import { Region } from 'src/app/model/region.model';
import { Department } from 'src/app/model/department';
import { Ville } from 'src/app/model/ville';
import { Sport } from 'src/app/model/sport';
import { SharedService } from 'src/app/services/shared.service';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';


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



   //  dropDown Section
   regionDetails: Region[];
   departmentDetails: Department[];
   villeDetails: Ville[];
   sportDetails: Sport[];


 private userProfileInfo: AuthUserProfile;

  constructor(
    private authService: AuthenticateService,
    private tokenStorage: TokenStorageService,
    private router: Router,
    private sharedService: SharedService
  ) {  }



  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isUserProfileFilled = true;
      this.roles = this.tokenStorage.getAuthorities();
      this.username = this.tokenStorage.getUsername();
      this.token = this.tokenStorage.getToken();
    }
    this.details();
  }

  details() {
    this.fetchRegionDetails();
    this.fetchDepartmentDetails();
    this.fetchVilleDetails();
    this.fetchSportDetails();
  }


  onValueChanged(formGroup: FormGroup) {
    if (!formGroup) { return; }
  }


  onSubmit() {
    // console.log('Submitted');
    // console.log('UserProfile Form', this.form);

    this.userProfileInfo = new AuthUserProfile(
      this.form.userRegion.regionName,
      this.form.userDepartment.departmentName,
      this.form.userVille.villeName,
      this.form.userSport.sportName,
      this.form.userPet,
      this.form.userFood
    );

    // console.log('Form', this.form);

    this.authService.userProfile(this.username, this.userProfileInfo).subscribe(
      data => {


        this.tokenStorage.saveToken(this.token);
        this.tokenStorage.saveUsername(this.username);

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
    // console.log('userProfileInfo', this.userProfileInfo);
  }

  reloadPage() {
    window.location.reload();
  }



  fetchRegionDetails() {
    this.sharedService.getRegions().subscribe((data: Region[]) => {
      this.regionDetails = data;
    });
  }

  fetchDepartmentDetails() {
    this.sharedService.getDepartments().subscribe((data: Department[]) => {
      this.departmentDetails = data;
    });
  }

  fetchVilleDetails() {
    this.sharedService.getVilles().subscribe((data: Ville[]) => {
      this.villeDetails = data;
    });
  }

  fetchSportDetails() {
    this.sharedService.getSports().subscribe((data: Sport[]) => {
      this.sportDetails = data;
    });
  }


}
