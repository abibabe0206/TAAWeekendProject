import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { RegisteredUsers } from '../model/registeredUsers.model';
import { SharedService } from '../services/shared.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

   regUsersDetails: Observable<RegisteredUsers[]>;
   board: string;
   errorMessage: string;

  constructor(
    private sharedService: SharedService,
   // private router: Router
   ) { }

  ngOnInit() {
     this.fetchregUsersDetails();
  }

  fetchregUsersDetails() {
    this.regUsersDetails = this.sharedService.getRegisteredUsers();
    this.sharedService.getAdminBoard().subscribe(
      data => {
        this.board = data;
      },
      error => {
        this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
      }
    );
  }

}
