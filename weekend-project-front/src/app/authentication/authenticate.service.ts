import { Injectable } from '@angular/core';

import { JwtResponse } from './jwt-response';
import { AuthLoginInfo } from '../model/login-info';
import { AuthUserProfile } from '../model/user-profile';
import { SignUpInfo } from '../model/sigup-info';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable, empty } from 'rxjs';
import { TokenStorageService } from './token-storage.service';



const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {

  private loginUrl = '/api/weekend/signin';
  private signupUrl = '/api/weekend/signup';
  private createUserProfile = '/api/info/weekend/userProfile';


  constructor(
    private http: HttpClient,
    private tokenStorage: TokenStorageService) { }

  attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  signUp(info: SignUpInfo): Observable<string> {
    return this.http.post<string>(this.signupUrl, info, httpOptions);
  }

  userProfile(username: string, credentials: AuthUserProfile): Observable<JwtResponse> {
    if (username) {
      return this.http.post<JwtResponse>(`${this.createUserProfile}/${username}`, credentials,  /*httpOptions,*/
    {
      headers: new HttpHeaders()
        .set('Authorization', 'Bearer ' + this.tokenStorage.getToken())
    });
    } else {
      // tslint:disable-next-line: deprecation
      return empty();
    }
  }

}
