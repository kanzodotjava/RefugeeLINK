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
