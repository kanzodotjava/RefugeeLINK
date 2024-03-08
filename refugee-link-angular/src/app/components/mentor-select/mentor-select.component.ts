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

  constructor(private mentorsService: ApiService) {}

  ngOnInit(): void {
    // Fetch mentors data
    this.mentorsService.getMentors().subscribe((data) => {
      this.mentors = data;
    });
  }

  applyToMentorship(mentorId: number): void {
    const username = localStorage.getItem('username');
    if (username) {
      this.mentorsService.applyToMentorship(username, mentorId).subscribe(
        (response) => {
          console.log(response); // Handle success response
          // Optionally, you can reload the mentors list after successfully applying to mentorship
        },
        (error) => {
          console.error(error); // Handle error response
        }
      );
    } else {
      console.error('Username not found in local storage');
    }
  }
}
