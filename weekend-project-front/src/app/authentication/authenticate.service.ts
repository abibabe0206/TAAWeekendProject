import { Injectable } from '@angular/core';

import { JwtResponse } from './jwt-response';
import { AuthLoginInfo } from '../model/login-info';
import { SignUpInfo } from '../model/sigup-info';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';



const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {

  private loginUrl = 'http://localhost:9002/api/weekend/signin';
  private signupUrl = 'http://localhost:9002/api/weekend/signup';


  constructor( private http: HttpClient) { }

  attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  signUp(info: SignUpInfo): Observable<string> {
    return this.http.post<string>(this.signupUrl, info, httpOptions);
  }
}
