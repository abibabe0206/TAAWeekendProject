import { Component, OnInit } from '@angular/core';
import { AuthUserProfile } from 'src/app/model/user-profile';
import { TokenStorageService } from 'src/app/authentication/token-storage.service';
import { SharedService } from 'src/app/services/shared.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Region } from 'src/app/model/region.model';
import { Department } from 'src/app/model/department';
import { Ville } from 'src/app/model/ville';
import { Sport } from 'src/app/model/sport';
import { FormGroup } from '@angular/forms';
import { AuthenticateService } from 'src/app/authentication/authenticate.service';


@Component({
  selector: 'app-edit-user-profile',
  templateUrl: './edit-user-profile.component.html',
  styleUrls: ['./edit-user-profile.component.css']
})
export class EditUserProfileComponent implements OnInit {

  form: any = {};
  id: string;
  profileDetails: AuthUserProfile;
  isUserProfileUpdated = false;
  isUserProfileUpdatedFailed = false;
  errorMessage = '';
  username: string;
  roles: string[] = [];
  token: string;

 //  dropDown Section
 regionDetails: Region[];
 departmentDetails: Department[];
 villeDetails: Ville[];
 sportDetails: Sport[];



  constructor(
    private authService: AuthenticateService,
    private tokenStorage: TokenStorageService,
    private sharedService: SharedService,
    private route: ActivatedRoute,
    private router: Router
    ) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isUserProfileUpdated = true;
      this.roles = this.tokenStorage.getAuthorities();
      this.username = this.tokenStorage.getUsername();
      this.token = this.tokenStorage.getToken();
    }
    this.existingProfile();
    this.details();
  }

  onValueChanged(formGroup: FormGroup) {
    if (!formGroup) { return; }
  }

  details() {
    this.fetchRegionDetails();
    this.fetchDepartmentDetails();
    this.fetchVilleDetails();
    this.fetchSportDetails();
  }

  existingProfile() {
    this.route.params.subscribe(params => {
      this.id = params.id;
      this.sharedService.getUserProfileById(this.id).subscribe(
        (data: AuthUserProfile) => {
          this.profileDetails = data;
          this.form.userRegion = this.profileDetails.userRegion;
          this.form.userDepartment = this.profileDetails.userDepartment;
          this.form.userVille = this.profileDetails.userVille;
          this.form.userSport = this.profileDetails.userSport;
          this.form.userPet = this.profileDetails.userPet;
          this.form.userFood = this.profileDetails.userFood;
        }
      );
    });
  }


  onSubmit() {
    // console.log('Submitted');
    // console.log('User Updated Profile Form', this.form);

    this.profileDetails = new AuthUserProfile(
      this.form.userRegion.regionName,
      this.form.userDepartment.departmentName,
      this.form.userVille.villeName,
      this.form.userSport.sportName,
      this.form.userPet,
      this.form.userFood
    );

    // console.log('Form', this.form);

    this.authService.updateUserProfile(this.id, this.profileDetails).subscribe(
      data => {


        this.tokenStorage.saveToken(this.token);
        this.tokenStorage.saveUsername(this.username);

        this.isUserProfileUpdatedFailed = false;
        this.isUserProfileUpdated = true;
        this.roles = this.tokenStorage.getAuthorities();
        this.username = this.tokenStorage.getUsername();
        // this.reloadPage();
        this.router.navigate(['/user']);
      },
      error => {
        console.log('Profile Update Error: ', error);
        this.errorMessage = error.error.message;
        this.isUserProfileUpdatedFailed = true;
      }
    );
    // console.log('profileDetails', this.profileDetails);
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
