import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private usernameKey = '';

  constructor() {}

  setUserType(userType: string): void {
    localStorage.setItem('userType', userType);
  }

  setUsername(username: string): void {
    localStorage.setItem('username', username);
  }

  getUsername(): string | null {
    return localStorage.getItem('username');
  }

  clearUsername(): void {
    localStorage.removeItem(this.usernameKey);
  }

  getUserType(): string | null {
    const userType = localStorage.getItem('userType');
    return userType !== null ? userType : 'default'; // Provide a default value if userType is null
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('userType');
  }

  logout(): void {
    localStorage.removeItem('userType');
  }
  getUserDetails(): any {
    return {
      userType: this.getUserType(),
    };
  }

  static isAdmin(): boolean {
    const userType = localStorage.getItem('userType');
    return userType === 'admin';
  }
}
