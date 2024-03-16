import { Component } from '@angular/core';
import { ApiService } from '../services/ApiService/api.service';
import { AuthService } from '../services/auth/auth.service';

@Component({
  selector: 'app-pfp',
  templateUrl: './pfp.component.html',
  styleUrls: ['./pfp.component.css']
})
export class PfpComponent {
  profilePictureUrl!: string;
  username!: string | null;
  mentor: any;

  constructor(private apiService: ApiService, private authService: AuthService) {
    this.username = this.authService.getUsername();
    this.apiService.getMentorUsernameByUsername(this.username!).subscribe(data => {
      this.mentor = data.username;
    this.profilePictureUrl = this.username
      ? `./assets/images/pfp/${this.mentor}.jpg`
        : './assets/images/pfp/default.jpg';
    });
  }

} 
    

