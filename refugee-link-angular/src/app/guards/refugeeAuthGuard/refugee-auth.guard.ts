import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';

@Injectable({
  providedIn: 'root',
})
export class RefugeeAuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean {
    const userType = this.authService.getUserType();
    if (userType === 'Refugee') {
      return true;
    } else {
      // Redirect to login page or any other page suitable for unauthorized access
      this.router.navigate(['/login']);
      return false;
    }
  }
}
