import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/ApiService/api.service';
@Component({
  selector: 'app-connected-refugees',
  templateUrl: './connected-refugees.component.html',
  styleUrls: ['./connected-refugees.component.css'],
})
export class ConnectedRefugeesComponent implements OnInit{
  refugeeInfoList: any[] = [];


  constructor(private apiService: ApiService) {}

  
  ngOnInit(): void {
    // Get username from local storage
    const username = localStorage.getItem('username');
    if (username) {
      // Make API call to fetch list of refugees
      this.apiService.getRefugeesByMentorUsername(username).subscribe(
        (response: any[]) => {
          this.refugeeInfoList = response;
        },
        (error) => {
          console.error('Error fetching refugees:', error);
        }
      );
    } else {
      console.error('Username not found in local storage');
    }
  }
}
