import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { ApiService } from 'src/app/services/ApiService/api.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
})
export class LoginPageComponent implements OnInit {
  loginForm!: FormGroup;
  username: string = '';
  password: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private apiService: ApiService
  ) {}

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const credentials = this.loginForm.value;
      this.apiService.login(credentials).subscribe(
        (response) => {
          // Handle successful login response
          console.log('Login successful:', response);
        },
        (error) => {
          // Handle error response
          console.error('Login error:', error);
        }
      );
    }
    console.log('Username:', this.username);
    console.log('Password:', this.password);
  }
}
