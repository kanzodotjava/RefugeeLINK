import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { ServicesDisplayComponent } from './components/services-display/services-display.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { RegisterPageComponent } from './components/register-page/register-page.component';
import { RegisterAsMentorComponent } from './components/register-as-mentor/register-as-mentor.component';
import { RegisterAsRefugeeComponent } from './components/register-as-refugee/register-as-refugee.component';
import { NewsComponent } from './components/news/news.component';
import { LoginPageComponent } from './components/login-page/login-page.component';

import { MentorSelectComponent } from './components/mentor-select/mentor-select.component';

import { AuthGuard } from './guards/auth/auth.guard';
import { PersonalPageComponent } from './components/personal-page/personal-page.component';
import { LogoutComponent } from './components/logout/logout.component';
import { ConnectedMentorComponent } from './components/connected-mentor/connected-mentor.component';
import { AdminLoginComponent } from './components/admin-login/admin-login.component';
import { ConnectedRefugeesComponent } from './components/connected-refugees/connected-refugees.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { ChatComponent } from './Chat-Component/chat/chat.component';
import { MessageListComponent } from './Chat-Component/message-list/message-list.component';
import { MessageFormComponent } from './Chat-Component/message-form/message-form.component';
import { MessageService } from './services/message.service';
import { OrganizationLoginComponent } from './components/organization-login/organization-login.component';
import { OrganizationDashboardComponent } from './components/organization-dashboard/organization-dashboard.component';
import { MentorPageComponent } from './Chat-Component/mentor-page/mentor-page.component';
import { RefugeeFormationsComponent } from './components/refugee-formations/refugee-formations.component';
import { FormationDetailsComponent } from './components/formation-details/formation-details.component';
import { RatingComponent } from './components/rating/rating.component';
import { ArrayFromNumberPipe } from './pipes/array-from-number.pipe';
import { StarRatingPipe } from './pipes/star-rating.pipe';
import { FormationListComponent } from './components/formation-list/formation-list.component';
import { FormationRefugeesComponent } from './components/formation-refugees/formation-refugees.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { MentorshipComponent } from './components/mentorship/mentorship.component';
import { LearningComponent } from './components/learning/learning.component';




@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    ServicesDisplayComponent,
    AboutUsComponent,
    HomePageComponent,
    RegisterPageComponent,
    RegisterAsMentorComponent,
    RegisterAsRefugeeComponent,
    NewsComponent,
    LoginPageComponent,
    MentorSelectComponent,
    PersonalPageComponent,
    LogoutComponent,
    ConnectedMentorComponent,
    AdminLoginComponent,
    ConnectedRefugeesComponent,
    AdminDashboardComponent,
    ChatComponent,
    MessageListComponent,
    MessageFormComponent,
    MessageListComponent,
    OrganizationLoginComponent,
    OrganizationDashboardComponent,
    MentorPageComponent,
    RefugeeFormationsComponent,
    FormationDetailsComponent,
    RatingComponent,
    ArrayFromNumberPipe,
    StarRatingPipe,
    FormationListComponent,
    FormationRefugeesComponent,
    NotFoundComponent,
    MentorshipComponent,
    LearningComponent,
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent],
})
export class AppModule {}
