// refugee-formations.component.ts

import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/ApiService/api.service';
import { AuthService } from 'src/app/services/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-refugee-formations',
  templateUrl: './refugee-formations.component.html',
  styleUrls: ['./refugee-formations.component.css'],
})
export class RefugeeFormationsComponent implements OnInit {
  formations: any[] = [];
  loggedInRefugeeId: number | undefined;
  applicationMessage: string = '';
  formationInfo: any;

  constructor(
    private apiService: ApiService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadFormations();
    const username = this.authService.getUsername();
    if (username !== null) {
      this.apiService.getCurrentFormationsByUsername(username).subscribe(
        (data) => {
          this.formationInfo = data; // Assign the response to formationInfo
        },
        (error) => {
          console.error('Error fetching formation:', error);
        }
      );
    } else {
      console.error('Username is null');
    }
  }

  loadFormations(): void {
    this.apiService.getFormationsByStatus('AWAITING_START').subscribe(
      (formations) => {
        this.formations = formations.map((formation: any) => ({
          ...formation,
          status: this.getStatusText(formation.status),
        }));
      },
      (error) => {
        console.error('Failed to load formations:', error);
        // Handle error if needed
      }
    );
  }

  getStatusText(status: string): string {
    switch (status) {
      case 'AWAITING_START':
        return 'Awaiting Start';
      // Add more cases for other status values if needed
      default:
        return status; // Default to the original status value
    }
  }

  applyToFormation(formationId: number): void {
    this.apiService.applyToFormation(1, formationId).subscribe(
      (response) => {
        this.applicationMessage = response;
      },
      (error) => {
        console.error('Failed to submit application:', error);
        this.applicationMessage = error.error; // Set the error message from the server response
      }
    );
  }
  goToFormationDetails(formationId: number): void {
    this.router.navigate(['/formation', formationId]); // Navigate to the formation details page
  }
}
