import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/ApiService/api.service';
import { AuthService } from 'src/app/services/auth/auth.service';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-personal-page',
  templateUrl: './personal-page.component.html',
  styleUrls: ['./personal-page.component.css'],
})
export class PersonalPageComponent implements OnInit {
  userDetails: any;
  profilePictureUrl: string;
  isRefugee: boolean = false; // Add this property
  removeMentorResponse: string = '';
  removeMentorResponseError: string = '';
  failedToUploadReponseError: string = '';


  constructor(
    private apiService: ApiService,
    private authService: AuthService,
    private http: HttpClient
  ) {
    const username = this.authService.getUsername();
    this.profilePictureUrl = username
      ? `./assets/images/pfp/${username}.jpg`
        : './assets/images/pfp/default.jpg';
  }

  ngOnInit(): void {
    const username = this.authService.getUsername();
    console.log('Username:', username);
    const userType = this.authService.getUserType();
    this.isRefugee = userType === 'Refugee';
    console.log('User type:', userType);
    if (username && userType) {
      this.apiService.getDetailsByUsername(username, userType).subscribe(
        (response) => {
          console.log('User details response:', response);
          this.userDetails = response; // Assign response to userDetails
        },
        (error) => {
          console.error('Error fetching user details:', error);
        }
      );
    } else {
      console.error('Username or user type is null');
      // Redirect to login page or display error message to user
    }
  }

  selectedFile: File | null = null;

  onFileSelected(event: Event): void {
    const target = event.target as HTMLInputElement;
    this.selectedFile = target.files ? target.files[0] : null;
  }


  upload() {
    const username = this.authService.getUsername(); // Retrieve the username
    if (!username) {
      console.error('Username is not available');
      return;
    }
  
    if (this.selectedFile) {
      const fd = new FormData();
      fd.append('image', this.selectedFile, this.selectedFile.name);
      const uploadUrl = `http://localhost:8080/file/upload/${username}`; // Use the username in the URL
  
      this.http.post(uploadUrl, fd).subscribe({
        next: (res) => {
          console.log(res);
          // Refresh the profile picture URL with a cache-busting query parameter
          this.profilePictureUrl = `./assets/images/pfp/${username}.jpg?` + new Date().getTime();
        },
        error: (err) => {
          console.error('Error uploading file:', err);
          this.failedToUploadReponseError = 'Failed to upload file. Make sure you are trying to upload .jpg files only!';
        }
      });
    } else {
      console.error('No file selected');
      this.failedToUploadReponseError = 'Please select a file to upload.';
    }
  }
  
  removeMentor() {
    if (this.isRefugee && this.userDetails?.id) {
      this.apiService.removeMentorFromRefugee(this.userDetails.id).subscribe({
        next: (response) => {
          console.log('Mentor removed successfully:', response);
          this.removeMentorResponse = 'Mentor removed successfully.';
        },
        error: (error) => {
          console.error('Error removing mentor:', error);
          this.removeMentorResponseError = 'Failed to remove mentor. You have no selected mentor!';
        }
      });
    } else {
      this.removeMentorResponseError = 'Operation not permitted. User is not a refugee or user details are missing.';
    }
  }
  closePopup() {
    this.removeMentorResponse = ''; 
    this.removeMentorResponseError = '';
    this.failedToUploadReponseError = '';
  }
}

