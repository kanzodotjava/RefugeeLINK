import { Component } from '@angular/core';
import { ApiService } from '../../services/ApiService/api.service';

@Component({
  selector: 'app-connected-mentor',
  templateUrl: './connected-mentor.component.html',
  styleUrls: ['./connected-mentor.component.css'],
})
export class ConnectedMentorComponent {
  mentor: any;

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    const username = localStorage.getItem('username');
    if (username) {
      this.apiService.getMentorByUsername(username).subscribe(
        (data) => {
          this.mentor = data;
        },
        (error) => {
          console.error('Error fetching mentor:', error);
        }
      );
    } else {
      console.error('Username not found in local storage');
    }
  }
}
