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

  ngOnInit(): void {
    const username = localStorage.getItem('username');
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

  goToChat(): void {
    this.router.navigate(['/messages']);
  }
}
