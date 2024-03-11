// admin-auth.guard.ts
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AdminAuthGuard {
  constructor(private router: Router) {}

  canActivate(): boolean {
    if (AuthService.isAdmin()) {
      return true;
    } else {
      // Redirect to admin login page if the user is not an admin
      this.router.navigate(['/admin-login']); // Change '/admin-login' to your admin login route
      return false;
    }
  }
}
