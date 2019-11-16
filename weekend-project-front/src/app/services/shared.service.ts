import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, empty } from 'rxjs';
import { AuthenticateService } from '../authentication/authenticate.service';
import { TokenStorageService } from '../authentication/token-storage.service';
import { AuthUserProfile } from '../model/user-profile';



@Injectable({
  providedIn: 'root'
})
export class SharedService {

  url = '/api/info/weekend';
 // url = 'http://localhost:9002/hello';

  constructor(
    private http: HttpClient,
    private tokenStorage: TokenStorageService) { }


  // to get a user info
  getUserBoard(): Observable<string> {
    return this.http.get(this.url + '/user', { responseType: 'text'});
  }

  // to get adim info
  getAdminBoard(): Observable<string> {
    return this.http.get(this.url + '/admin', { responseType: 'text' });
  }

  // to get list of all regions
  getRegions(): Observable<any> {
    return this.http.get(this.url + '/region');
  }

  // to get list of all departments
  getDepartments() {
    return this.http.get(this.url + '/department');
  }

  // to get list of all villes
  getVilles() {
    return this.http.get(this.url + '/ville');
  }

  // to get list of all sports
  getSports() {
    return this.http.get(this.url + '/sport');
  }

  // to get list of all registered Users
  getRegisteredUsers(): Observable<any> {
    return this.http.get(this.url + '/registerUser');
  }

  // to get the list of a user profile
  getProfiles(): Observable<any> {
    return this.http.get(this.url + '/userProfile');
  }

  // to get the user profile details for a particaler use.
  getUserProfile(userName: string): Observable<AuthUserProfile> {
    if (userName) {
      return this.http.get<AuthUserProfile>(`${this.url}/userProfile/${userName}`, {
        headers: new HttpHeaders()
          .set('Authorization', 'Bearer ' + this.tokenStorage.getToken())
      });
    } else {
      // tslint:disable-next-line: deprecation
      return empty();
    }
  }


  // get profile by id
  getUserProfileById(id: string): Observable<AuthUserProfile> {
    if (id) {
      return this.http.get<AuthUserProfile>(`${this.url}/userProfileId/${id}`, {
        headers: new HttpHeaders()
          .set('Authorization', 'Bearer ' + this.tokenStorage.getToken())
      });
    } else {
      // tslint:disable-next-line: deprecation
      return empty();
    }
  }

  // deleting profile by id
  deleteUserProfileById(id: string): Observable<AuthUserProfile> {
    if (id) {
      return this.http.delete<AuthUserProfile>(`${this.url}/userProfile/${id}`, {
        headers: new HttpHeaders()
          .set('Authorization', 'Bearer ' + this.tokenStorage.getToken())
      });
    } else {
      // tslint:disable-next-line: deprecation
      return empty();
    }
  }

}
