import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import {TableModule} from 'primeng/table';


import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { HomeComponent } from './home/home.component';
import { RegionComponent } from './region/region.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AdminComponent } from './admin/admin.component';
import { UserComponent } from './user/user.component';

import { httpInterceptorProviders } from './authentication/auth-interceptor';
import { ViewUserProfileComponent } from './userProfile/view-user-profile/view-user-profile.component';
import { EditUserProfileComponent } from './userProfile/edit-user-profile/edit-user-profile.component';
import { CreateUserProfileComponent } from './userProfile/create-user-profile/create-user-profile.component';


@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    HomeComponent,
    RegionComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
    AdminComponent,
    UserComponent,
    ViewUserProfileComponent,
    EditUserProfileComponent,
    CreateUserProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    TableModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
