import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/ApiService/api.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'mentor-select',
  templateUrl: './mentor-select.component.html',
  styleUrls: ['./mentor-select.component.css'],
})
export class MentorSelectComponent implements OnInit {
  mentors: any[] = [];
  errorMessage: string = '';

  constructor(private mentorsService: ApiService) {}

  ngOnInit(): void {
    // Fetch mentors data
    this.mentorsService.getMentors().subscribe(
      (data) => {
        this.mentors = data.map((mentor: any) => ({
          ...mentor,
          applicationResponse: null, // Add a property to store the application response
        }));
      },
      (error) => {
        console.error('Error fetching mentors:', error);
      }
    );
  }

  applyToMentorship(mentorId: number, mentor: any): void {
    const username = localStorage.getItem('username');
    if (username) {
      this.mentorsService.applyToMentorship(username, mentorId).subscribe(
        (response) => {
          console.log(response); // Handle success response
          mentor.applicationResponse = response; // Update application response
        },
        (error) => {
          console.error('Error applying to mentorship:', error);
          this.errorMessage = error.error; // Extract error message
        }
      );
    } else {
      console.error('Username not found in local storage');
    }
  }
}
