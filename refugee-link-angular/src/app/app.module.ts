import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { ServicesDisplayComponent } from './components/services-display/services-display.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { RegisterPageComponent } from './components/register-page/register-page.component';
import { RegisterAsMentorComponent } from './components/register-as-mentor/register-as-mentor.component';

@NgModule({
  declarations: [AppComponent, NavBarComponent, ServicesDisplayComponent, AboutUsComponent, HomePageComponent, RegisterPageComponent, RegisterAsMentorComponent],
  imports: [BrowserModule, AppRoutingModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
