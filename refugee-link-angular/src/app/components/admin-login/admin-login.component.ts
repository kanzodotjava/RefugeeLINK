import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css'],
})
export class AdminLoginComponent implements OnInit {
  loginForm: FormGroup;
  message!: string;

  constructor(private http: HttpClient, private formBuilder: FormBuilder) {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      const formData = this.loginForm.value;
      this.http.post('https://localhost:7165/admin/login', formData).subscribe(
        (response: any) => {
          // Check if the response contains a message property
          if (response && response.message) {
            // Display the success message
            this.message = response.message;
          } else {
            // If no message property found, display a generic success message
            this.message = 'Login successful';
          }
        },
        (error) => {
          // Display error message
          this.message =
            'An error occurred while logging in. Please try again.';
        }
      );
    }
  }
}
