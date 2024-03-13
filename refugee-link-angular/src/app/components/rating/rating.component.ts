import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../../services/auth/auth.service';
import { ApiService } from '../../services/ApiService/api.service';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.css'],
})
export class RatingComponent{
  mentorUsername: any;
  refugeeUsername = this.authService.getUsername();
  canRate: boolean = true;
  currentRating: number = 0;
  

  constructor(
    private authService: AuthService,
    private apiService: ApiService,
    private http: HttpClient
  ) {
    this.apiService
      .getMentorUsernameByUsername(this.refugeeUsername!)
      .subscribe((data) => {
        this.mentorUsername = data.username;
        this.checkIfUserHasRated();
        this.getRating();
      });
  }


  checkIfUserHasRated() {
    const url = `https://localhost:7165/has-rated`;
    const body = {
      mentorUsername: this.mentorUsername,
      userUsername: this.refugeeUsername,
    };

    this.http.post(url, body).subscribe({
      next: (response: any) => {
        // Assuming the response contains a boolean indicating if the user has rated
        this.canRate = !response.hasRated;
      },
      error: (error) => {
        console.error('Error checking if user has rated', error);
        // Handle error, possibly by disabling rating or showing an error message
      },
    });
  }

  updateRating(rating: number) {
    this.currentRating = rating; // Update visual rating
    if (this.canRate) {
      this.rate(rating); // Proceed to submit the rating only if the user can rate
    }// Proceed to submit the rating
  }

  rate(rating: number) {
    // Convert the rating to a double
    const doubleRating: number = parseFloat(rating.toFixed(1));

    const url = 'https://localhost:7165/rate-mentor';
    const body = {
      mentorUsername: this.mentorUsername,
      userUsername: this.refugeeUsername,
      rating: doubleRating,
    };

    // Log the JSON object before sending the request
    console.log('JSON to be sent:', body);

    this.http.post(url, body).subscribe({
      next: (response) => {
        console.log('Rating submitted successfully', response);
        this.checkIfUserHasRated();
      },
      error: (error) => {
        console.error('Failed to submit rating', error);
      },
    });
  }

  getRating() {
    const url = `https://localhost:7165/get-rating`;
    const body = {
      mentorUsername: this.mentorUsername,
      userUsername: this.refugeeUsername,
    };

    this.http.post(url, body).subscribe({
      next: (response: any) => {
        this.currentRating = response.rating;
      },
      error: (error) => {
        console.error('Error retrieving rating', error);
      },
    });
  }
}



