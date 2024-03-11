import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/ApiService/api.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css'],
})
export class AdminDashboardComponent implements OnInit {
  mentors: any[] = [];

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.fetchMentors();
  }

  fetchMentors() {
    this.apiService.getMentors().subscribe((mentors: any[]) => {
      this.mentors = mentors;
    });
  }

  acceptMentor(mentorId: number) {
    this.apiService.acceptMentor(mentorId).subscribe(() => {
      this.fetchMentors(); // Refresh the mentors list
    });
  }

  rejectMentor(mentorId: number) {
    this.apiService.rejectMentor(mentorId).subscribe(() => {
      this.fetchMentors(); // Refresh the mentors list
    });
  }
}
