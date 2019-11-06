import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { RegionComponent } from './region/region.component';
import { UserComponent } from './user/user.component';
import { AdminComponent } from './admin/admin.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { CreateUserProfileComponent } from './userProfile/create-user-profile/create-user-profile.component';
import { EditUserProfileComponent } from './userProfile/edit-user-profile/edit-user-profile.component';
import { ViewUserProfileComponent } from './userProfile/view-user-profile/view-user-profile.component';


const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'region',
    component: RegionComponent
  },
  {
    path: 'user',
    component: UserComponent
  },
  {
    path: 'admin',
    component: AdminComponent
  },
  {
    path: 'auth/login',
    component: LoginComponent
  },
  {
    path: 'signup',
    component: RegisterComponent
  },
  {
    path: 'createUserProfile',
    component: CreateUserProfileComponent
  },
  {
    path: 'editUserProfile/:id',
    component: EditUserProfileComponent
  },
  {
    path: 'viewUserProfile',
    component: ViewUserProfileComponent
  },
  {
    path: 'userProfile',
    redirectTo: 'viewUserProfile',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
