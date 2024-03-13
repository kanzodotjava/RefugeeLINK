import { Component } from '@angular/core';
import { ApiService } from '../../services/ApiService/api.service';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-connected-mentor',
  templateUrl: './connected-mentor.component.html',
  styleUrls: ['./connected-mentor.component.css'],
})
export class ConnectedMentorComponent {
  mentor: any;
  profilePictureUrl!: string;
  username!: string | null;

  constructor(private apiService: ApiService, private authService: AuthService) {
    this.username = this.authService.getUsername();
    this.apiService.getMentorUsernameByUsername(this.username!).subscribe(data => {
      this.mentor = data.username;
    this.profilePictureUrl = this.username
      ? `./assets/images/pfp/${this.mentor}.jpg`
        : './assets/images/pfp/default.jpg';
    });
  }
  

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
