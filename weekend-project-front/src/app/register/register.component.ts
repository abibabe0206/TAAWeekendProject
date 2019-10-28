import { Component, OnInit } from '@angular/core';

import { AuthenticateService } from '../authentication/authenticate.service';
import { SignUpInfo } from '../model/sigup-info';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form: any = {};
  signupInfo: SignUpInfo;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthenticateService) { }

  ngOnInit() {
  }

  onSubmit() {
    console.log('signUP form', this.form);

    this.signupInfo = new SignUpInfo(
      this.form.name,
      this.form.username,
      this.form.email,
      this.form.password
    );

    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        console.log('signUp data', data);
        this.isSignedUp = true;
        this.isSignUpFailed = false;
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }
    );

  }

}
