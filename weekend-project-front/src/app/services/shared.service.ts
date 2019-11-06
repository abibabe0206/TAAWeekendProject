import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class SharedService {

  url = 'http://localhost:9002/api/info/weekend';
 // url = 'http://localhost:9002/hello';


  constructor(private http: HttpClient) { }


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

  // to get list of all registered Users
  getRegisteredUsers(): Observable<any> {
    return this.http.get(this.url + '/registerUser');
  }

  // to get the list of a user profile
  getProfiles(): Observable<any> {
    return this.http.get(this.url + '/userProfile');
  }

  // to get the user profile details for a particaler use.
  getUserProfile(userName: string) {
    return this.http.get(`${this.url}/userProfile/${userName}`);
  }


}
