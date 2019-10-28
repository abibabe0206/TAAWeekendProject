import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../authentication/token-storage.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  appTitle = 'Weekend Application';

  private roles: string[];
  public authority: string;

  constructor(private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    this.authentication();
  }

  authentication() {
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
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
