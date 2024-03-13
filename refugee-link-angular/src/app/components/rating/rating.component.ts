import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../../services/auth/auth.service';
import { ApiService } from '../../services/ApiService/api.service';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.css']
})
export class RatingComponent {

  mentorUsername: any;
  refugeeUsername = this.authService.getUsername();

  constructor(private authService: AuthService, private apiService: ApiService, private http: HttpClient) {
    this.apiService.getMentorUsernameByUsername(this.refugeeUsername!).subscribe(data => {
      this.mentorUsername = data.username;
    }
    );
  }

  rate(rating: number) {
    const url = 'http://localhost:7165/rate-mentor';
    const body = {
      mentorUsername: this.mentorUsername,
      refugeeUsername: this.refugeeUsername,
      rating: rating
    };


    this.http.post(url, body).subscribe({
      next: (response) => {
        console.log('Rating submitted successfully', response);
      },
      error: (error) => {
        console.error('Failed to submit rating', error);
      }
    });
  }
}
