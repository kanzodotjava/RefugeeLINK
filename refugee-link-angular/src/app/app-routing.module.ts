import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { RegisterPageComponent } from './components/register-page/register-page.component';
import { RegisterAsMentorComponent } from './components/register-as-mentor/register-as-mentor.component';

const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'about-us', component: AboutUsComponent },
  { path: 'register', component: RegisterPageComponent },
  { path: 'register-as-mentor', component: RegisterAsMentorComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
