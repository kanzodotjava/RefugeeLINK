import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/ApiService/api.service';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-personal-page',
  templateUrl: './personal-page.component.html',
  styleUrls: ['./personal-page.component.css'],
})
export class PersonalPageComponent implements OnInit {
  userDetails: any;

  constructor(
    private apiService: ApiService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    const username = this.authService.getUsername();
    console.log('Username:', username);
    const userType = this.authService.getUserType();
    console.log('User type:', userType);
    if (username && userType) {
      this.apiService.getDetailsByUsername(username, userType).subscribe(
        (response) => {
          console.log('User details response:', response);
          this.userDetails = response; // Assign response to userDetails
        },
        (error) => {
          console.error('Error fetching user details:', error);
        }
      );
    } else {
      console.error('Username or user type is null');
      // Redirect to login page or display error message to user
    }
  }
}
