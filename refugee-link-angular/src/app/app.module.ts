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
import { ChatComponent } from './components/chat/chat.component';
import { WebSocketService } from './services/web-socket.service';


@NgModule({
  declarations: [
    AppComponent,
    ChatComponent,
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

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [AuthGuard, WebSocketService],
  bootstrap: [AppComponent],
})
export class AppModule {}
