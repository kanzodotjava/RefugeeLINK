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
  loggedInRefugeeId!: number ;
  applicationMessage: string = '';
  formationInfo: any;
  username: string | null | undefined;

  constructor(
    private apiService: ApiService,
    private authService: AuthService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {
    this.username = this.authService.getUsername();
    if (this.username !== null) {
      this.apiService.getRefugeeIdByUsername(this.username!).subscribe(
        (id) => {
          this.loggedInRefugeeId = id;
        },
        (error) => {
          console.error('Error fetching refugee id:', error);
        }
      );
    } else {
      console.error('Username is null');
    }
  }
  

  ngOnInit(): void {
    this.loadFormations();
    if (this.username !== null) {
      this.apiService.getCurrentFormationsByUsername(this.username!).subscribe(
        (data) => {
          this.formationInfo = data; 
          this.apiService.getOrganizationNameById(this.formationInfo.organizationId).subscribe(
            (data) => {
              this.formationInfo.organizationName = data; 
            }
          )
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
    const statuses = ['AWAITING_START', 'ONGOING', 'COMPLETED'];
    statuses.forEach(status => {
      this.apiService.getFormationsByStatus(status).subscribe(
        (formations) => {
          if (!formations.length) {
            console.log(`No ${status} formations to display.`);
            return;
          }
  
          const formationObservables = formations.map((formation: any) => 
            this.apiService.getOrganizationNameById(formation.organizationId).pipe(
              map(organizationName => ({
                ...formation,
                status: this.getStatusText(formation.status),
                organizationName
              })),
              catchError(error => {
                console.error('Error fetching organization name:', error);
                return of({ ...formation, organizationName: 'Unknown' });
              })
            )
          );
  
          combineLatest(formationObservables).subscribe(
            (formationsWithOrgNames) => {
              console.log(`Formations with ${status} status and organization names:`, formationsWithOrgNames);
              this.formations = this.formations.concat(formationsWithOrgNames); 
              this.cdr.detectChanges();
            },
            (error) => {
              console.error(`Failed to load ${status} formations with organization names:`, error);
            }
          );
        },
        (error) => {
          console.error(`Failed to load ${status} formations:`, error);
        }
      );
    });
  }
  
  

  getStatusText(status: string): string {
    switch (status) {
      case 'AWAITING_START':
        return 'Awaiting Start';
      default:
        return status; 
    }
  }

  leaveFormation(): void {
    const refugeeId = this.loggedInRefugeeId;
    const formationId = this.formationInfo.id;
        this.apiService.expelRefugeeFromFormation(refugeeId, formationId).subscribe(
      (response) => {
        this.applicationMessage = response;
          window.location.reload();
      }
    )
  }

  applyToFormation(formationId: number): void {

    this.apiService.applyToFormation(this.loggedInRefugeeId!, formationId).subscribe(
      (response) => {
        window.location.reload();
      },
      (error) => {
        console.error('Failed to submit application:', error);
        this.applicationMessage = error.error; 
      }
      
    );
  }
  goToFormationDetails(formationId: number): void {
    this.router.navigate(['/formation', formationId]); 
  }

  closePopup() {
    this.applicationMessage = ''; 
  }
}
