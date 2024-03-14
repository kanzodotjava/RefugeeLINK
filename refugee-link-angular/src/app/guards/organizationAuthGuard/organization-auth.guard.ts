import { CanActivate } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class OrganizationAuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean {
    // Check if the user type in local storage is 'organization'
    const userType = this.authService.getUserType();
    if (userType === 'organization') {
      return true; // Allow access to the component
    } else {
      // Redirect to the login page or any other appropriate route
      this.router.navigate(['/organization-login']);
      return false; // Disallow access to the component
    }
  }
}
