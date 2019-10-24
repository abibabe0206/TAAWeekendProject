import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class SharedService {

 // url = 'http://localhost:9002/hello';
 url = 'http://localhost:9002/api/weekend';

  constructor(private http: HttpClient) { }

  // tslint:disable-next-line: ban-types
  // getRegions() {
  //   return this.http.get(this.url + '/registerUser');
  // }
  getRegions(): Observable<any> {
    return this.http.get(this.url + '/registerUser');
  }
  // getRegions(): Observable<any> {

  //   return this.http.get('http://localhost:9002/hello', {responseType: 'text'});
  // }

}
