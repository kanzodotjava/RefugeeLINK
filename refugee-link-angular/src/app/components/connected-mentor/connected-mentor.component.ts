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
  }
  

  ngOnInit(): void {
    if (this.username) {
      this.apiService.getMentorByUsername(this.username).subscribe(
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
