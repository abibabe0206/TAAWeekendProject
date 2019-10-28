import { Component, OnInit } from '@angular/core';
import { Region } from '../model/region.model';
import { SharedService } from '../services/shared.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-region',
  templateUrl: './region.component.html',
  styleUrls: ['./region.component.css']
})
export class RegionComponent implements OnInit {

   //  regionDetails: Region[];
   regionDetails: Observable<Region[]>;

  constructor(
    private sharedService: SharedService,
   // private router: Router
   ) { }

  ngOnInit() {
     this.fetchRegionDetails();
  }

  // fetchRegionDetails() {
  //   this.sharedService.getRegions().subscribe((data: Region[]) => {
  //     this.regionDetails = data;
  //     console.log('All Regions' + this.regionDetails);
  //   });
  // }

  fetchRegionDetails() {
    this.regionDetails = this.sharedService.getRegions();
  }
}
