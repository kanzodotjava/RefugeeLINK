import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { RegisterPageComponent } from './components/register-page/register-page.component';
import { RegisterAsMentorComponent } from './components/register-as-mentor/register-as-mentor.component';
import { RegisterAsRefugeeComponent } from './components/register-as-refugee/register-as-refugee.component';
import { LoginPageComponent } from './components/login-page/login-page.component';

import { MentorSelectComponent } from './components/mentor-select/mentor-select.component';

import { PersonalPageComponent } from './components/personal-page/personal-page.component';
import { AuthGuard } from './guards/auth/auth.guard';
import { LogoutComponent } from './components/logout/logout.component';
import { ChatComponent } from './components/chat/chat.component';


const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'about-us', component: AboutUsComponent },
  { path: 'register', component: RegisterPageComponent },
  { path: 'register-as-mentor', component: RegisterAsMentorComponent },
  { path: 'register-as-refugee', component: RegisterAsRefugeeComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'mentor-select', component: MentorSelectComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'personal-page', component: PersonalPageComponent, canActivate: [AuthGuard] },
  { path: 'chat/:userId', component: ChatComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
