import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from 'src/app/services/ApiService/api.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-organization-login',
  templateUrl: './organization-login.component.html',
  styleUrls: ['./organization-login.component.css'],
})
export class OrganizationLoginComponent implements OnInit {
  loginForm!: FormGroup;
  message!: string;

  constructor(
    private fb: FormBuilder,
    private apiService: ApiService,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.createLoginForm();
  }

  createLoginForm(): void {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      const formData = this.loginForm.value;
      const username = formData.username;
      // Call your API service method for organization login
      this.apiService.organizationLogin(formData).subscribe(
        (response) => {
          // Handle successful login response
          console.log('Organization logged in:', response);
          // Set user type in local storage
          this.authService.setUserType('organization');
          this.authService.setUsername(username);
          console.log(this.authService.getUsername);
          // Redirect to organization dashboard
          this.router.navigate(['/organization-dashboard']);
        },
        (error) => {
          // Handle error response
          console.error('Login failed:', error);
          this.message = 'Invalid username or password.';
        }
      );
    } else {
      // Mark form controls as touched to display validation errors
      this.markFormGroupTouched(this.loginForm);
    }
  }

  markFormGroupTouched(formGroup: FormGroup): void {
    Object.values(formGroup.controls).forEach((control) => {
      control.markAsTouched();

      if (control instanceof FormGroup) {
        this.markFormGroupTouched(control);
      }
    });
  }
}
