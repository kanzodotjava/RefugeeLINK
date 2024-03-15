// refugee-formations.component.ts

import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/ApiService/api.service';
import { AuthService } from 'src/app/services/auth/auth.service';
import { Router } from '@angular/router';
import { map, combineLatest, catchError, of } from 'rxjs';

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
    private router: Router,
    private cdr: ChangeDetectorRef
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
        if (!formations.length) {
          console.log('No formations to display.');
          return;
        }
  
        const formationObservables = formations.map((formation: any) => 
        
        this.apiService.getOrganizationNameById(formation.organizationId).pipe(
          
          map(organizationName => ({
            ...formation,
            status: this.getStatusText(formation.status),
            organizationName // Since organizationName is already a string
            
          })),
          catchError(error => {
            console.error('Error fetching organization name:', error);
            return of({ ...formation, organizationName: 'Unknown' }); // Provide a fallback
          })
        )
      );
        combineLatest(formationObservables).subscribe(
          (formationsWithOrgNames) => {
            console.log('Formations with organization names:', formationsWithOrgNames);
            this.formations = formationsWithOrgNames;
            this.cdr.detectChanges(); // Manually trigger change detection if needed
          },
          (error) => {
            console.error('Failed to load formations with organization names:', error);
          }
        );
      },
      (error) => {
        console.error('Failed to load formations:', error);
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
        this.ngOnInit();
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
