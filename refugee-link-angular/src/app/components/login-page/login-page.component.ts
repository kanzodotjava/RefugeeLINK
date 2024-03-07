import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router'; // Import Router
import { ApiService } from 'src/app/services/ApiService/api.service';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
})
export class LoginPageComponent implements OnInit {
  loginForm!: FormGroup;
  username: string = '';
  password: string = '';
  errorMessage: string = '';
  loginFailed: boolean = false; // Flag to indicate if login failed

  constructor(
    private formBuilder: FormBuilder,
    private apiService: ApiService,
    private authService: AuthService,
    private router: Router // Inject Router
  ) {}

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      const credentials = this.loginForm.value;
      this.apiService.login(credentials).subscribe(
        (response) => {
          console.log('Login successful:', response);
          if (response.userType) {
            this.authService.setUserType(response.userType);
          }
          if (response.username) {
            this.authService.setUsername(response.username); // Save username to local storage
          }
          this.router.navigate(['/']); // Redirect to homepage
        },
        (error) => {
          console.error('Login error:', error);
          this.errorMessage = 'Invalid credentials. Please try again.';
          this.loginFailed = true;
        }
      );
    }
  }
}
