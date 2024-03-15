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
import { ConnectedMentorComponent } from './components/connected-mentor/connected-mentor.component';
import { AdminLoginComponent } from './components/admin-login/admin-login.component';
import { ConnectedRefugeesComponent } from './components/connected-refugees/connected-refugees.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { AdminAuthGuard } from './guards/adminAuthGuard/admin-auth.guard';
import { OrganizationLoginComponent } from './components/organization-login/organization-login.component';
import { OrganizationDashboardComponent } from './components/organization-dashboard/organization-dashboard.component';
import { MentorPageComponent } from './Chat-Component/mentor-page/mentor-page.component';
import { MessageListComponent } from './Chat-Component/message-list/message-list.component';
import { RefugeeFormationsComponent } from './components/refugee-formations/refugee-formations.component';
import { FormationDetailsComponent } from './components/formation-details/formation-details.component';
import { OrganizationAuthGuard } from './guards/organizationAuthGuard/organization-auth.guard';
import { RefugeeAuthGuard } from './guards/refugeeAuthGuard/refugee-auth.guard';
import { MentorAuthGuard } from './guards/mentorAuthGuard/mentor-auth.guard';
import { FormationListComponent } from './components/formation-list/formation-list.component';
import { FormationRefugeesComponent } from './components/formation-refugees/formation-refugees.component';
import { NotFoundComponent } from './components/not-found/not-found.component';

const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'about-us', component: AboutUsComponent },
  { path: 'register', component: RegisterPageComponent },
  { path: 'register-as-mentor', component: RegisterAsMentorComponent },
  { path: 'register-as-refugee', component: RegisterAsRefugeeComponent },
  { path: 'login', component: LoginPageComponent },
  {
    path: 'mentor-select',
    component: MentorSelectComponent,
    canActivate: [RefugeeAuthGuard],
  },
  { path: 'logout', component: LogoutComponent },
  { path: 'admin-login', component: AdminLoginComponent },
  {
    path: 'connected-refugees',
    component: ConnectedRefugeesComponent,
    canActivate: [MentorAuthGuard],
  },
  {
    path: 'admin-dashboard',
    component: AdminDashboardComponent,
    canActivate: [AdminAuthGuard],
  },
  { path: 'organization-login', component: OrganizationLoginComponent },
  {
    path: 'organization-dashboard',
    component: OrganizationDashboardComponent,
    canActivate: [OrganizationAuthGuard],
  },
  {
    path: 'organization-formations',
    component: FormationListComponent,
    canActivate: [OrganizationAuthGuard],
  },
  {
    path: 'organization-formations-refugees/:id',
    component: FormationRefugeesComponent,
    canActivate: [OrganizationAuthGuard],
  },

  {
    path: 'personal-page',
    component: PersonalPageComponent,
    canActivate: [AuthGuard],
  },
  { path: 'connected-mentor', component: ConnectedMentorComponent },
  { path: 'chat', component: MessageListComponent },
  { path: 'messages', component: MentorPageComponent },

  { path: 'refugee-formations', component: RefugeeFormationsComponent },
  { path: 'formation/:id', component: FormationDetailsComponent },
  { path: '**', component: NotFoundComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
