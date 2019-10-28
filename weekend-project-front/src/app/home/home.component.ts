import { Component, OnInit } from '@angular/core';

import { TokenStorageService } from '../authentication/token-storage.service';



@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  info: any;
  public authority: string;
  private roles: string[];

  constructor(private token: TokenStorageService) { }

  ngOnInit() {
    this.details();
    this.test();
  }

  details() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
  }

  logOut() {
    this.token.signOut();
    window.location.reload();
  }

  test() {
    if (this.token.getToken()) {
      this.roles = this.token.getAuthorities();
      this.roles.every(role => {
        if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
          return false;
        } else {
          this.authority = 'user';
          return true;
        }
      });
    }
  }

}
