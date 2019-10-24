import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { RegionComponent } from './region/region.component';


const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'region',
    component: RegionComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
