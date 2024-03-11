import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/ApiService/api.service';
import { ChatService } from '../../services/chat.service';
import { Router } from '@angular/router';

@Component({
  selector: 'mentor-select',
  templateUrl: './mentor-select.component.html',
  styleUrls: ['./mentor-select.component.css'],
})
export class MentorSelectComponent implements OnInit {
  mentors: any[] = []; // Assuming your API returns an array of mentor objects
  errorMessage: string = '';

  constructor(
    private mentorsService: ApiService,
    private chatService: ChatService,
    private router: Router
  ) { }

  ngOnInit(): void {
    // Fetch mentors data
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
          mentor.applicationResponse = 'Application successful'; // Simplified response handling for clarity
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

  selectChatPartner(mentorUsername: string): void {
    // Debugging log to check the received mentorUsername
    console.log("Mentor username received:", mentorUsername);

    if (!mentorUsername) {
      console.error("Mentor username is undefined or not provided.");
      return;
    }

    // Navigate to the chat component, assuming your app is designed to
    // use the logged-in user's username from AuthService within the ChatComponent
    this.router.navigate(['/chat', mentorUsername]);
  }
}
