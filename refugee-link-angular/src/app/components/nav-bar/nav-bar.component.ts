import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css'],
})
export class NavBarComponent implements OnInit {
  constructor(private authService: AuthService) {}

  ngOnInit(): void {}

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn(); // Implement isAuthenticated() method in AuthService
  }

  logout(): void {
    this.authService.logout(); // Implement logout() method in AuthService
  }
  getUserType(): string | null {
    return this.authService.getUserType();
  }
}
