// mentor-select.component.ts

import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/ApiService/api.service';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'mentor-select',
  templateUrl: './mentor-select.component.html',
  styleUrls: ['./mentor-select.component.css'],
})
export class MentorSelectComponent implements OnInit {
  mentors: any[] = [];
  errorMessage: string = '';

  constructor(
    private mentorsService: ApiService,
    private router: Router,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.mentorsService.getMentors().subscribe(
      (data) => {
        this.mentors = data;
      },
      (error) => {
        console.error('Error fetching mentors:', error);
        this.errorMessage = 'Failed to load mentors.';
      }
    );
  }

  applyToMentorship(mentorId: number, mentor: any): void {
    const username = localStorage.getItem('username');
    if (username) {
      this.mentorsService.applyToMentorship(username, mentorId).subscribe(
        (response) => {
          console.log('Mentorship application successful:', response);
          mentor.applicationResponse = 'Application successful';
        },
        (error) => {
          console.error('Error applying to mentorship:', error);
          this.errorMessage = 'Application failed. Please try again.';
        }
      );
    } else {
      console.error('Username not found in local storage');
      this.errorMessage = 'You must be logged in to apply for mentorship.';
    }
  }
}
