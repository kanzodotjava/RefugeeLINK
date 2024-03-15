import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/ApiService/api.service';
import { retry, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-connected-refugees',
  templateUrl: './connected-refugees.component.html',
  styleUrls: ['./connected-refugees.component.css'],
})
export class ConnectedRefugeesComponent implements OnInit {
  refugeeInfoList: any[] = [];
  profilePictureUrl: string = './assets/images/pfp/';
  constructor(private apiService: ApiService,
  private router: Router) { }
  public userStatus: string = '';
  ngOnInit(): void {
    const username = localStorage.getItem('username');
    this.getStatus(username || '');

    if (username) {
      this.apiService
        .getRefugeesByMentorUsername(username)
        .pipe(
          retry(3), // Retry the request up to 3 times
          catchError((error) => {
            console.error('Error fetching refugees:', error);
            // Handle the error here, e.g., display a message to the user
            return throwError(error);
          })
        )
        .subscribe((response: any[]) => {
          this.refugeeInfoList = response;
        });
    } else {
      console.error('Username not found in local storage');
    }
  }

  getStatus(username: string) {
    this.apiService.getStatusByUsername(username).subscribe(status => {
      // Remove quotes from the status string
      this.userStatus = status.replace(/['"]+/g, '');
      console.log(this.userStatus); // This will log the actual status without quotes, e.g., REJECTED
    }, error => {
      console.error('Error fetching status:', error);
    });
  }

  goToChat(): void {
    this.router.navigate(['/messages']); // Adjust the route as per your application's routing
  }
}
