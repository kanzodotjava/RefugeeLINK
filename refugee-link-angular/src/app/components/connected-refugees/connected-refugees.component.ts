import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/ApiService/api.service';
@Component({
  selector: 'app-connected-refugees',
  templateUrl: './connected-refugees.component.html',
  styleUrls: ['./connected-refugees.component.css'],
})
export class ConnectedRefugeesComponent implements OnInit{
  refugeeInfo: any;

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    // Get username from local storage
    const username = localStorage.getItem('username');
    if (username) {
      // Make API call to fetch refugee info
      this.apiService.getRefugeeByUsername(username).subscribe(
        (response: any) => {
          this.refugeeInfo = response;
        },
        (error) => {
          console.error('Error fetching refugee info:', error);
        }
      );
    } else {
      console.error('Username not found in local storage');
    }
  }
}
