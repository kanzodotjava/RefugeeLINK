import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/services/ApiService/api.service';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-formation-list',
  templateUrl: './formation-list.component.html',
  styleUrls: ['./formation-list.component.css']
})
export class FormationListComponent implements OnInit {
  formations: any[] = [];
  statuses = ['AWAITING_START', 'ONGOING', 'COMPLETED', 'CANCELLED'];
  statusDisplayNames = ['Awaiting Start', 'Ongoing', 'Completed', 'Cancelled'];


  constructor(
    private apiService: ApiService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.fetchFormations();
  }


  fetchFormations(): void {
    const username = this.authService.getUsername();
    if (username) {
      this.apiService.getFormationsByUsername(username).subscribe(
        (formations) => {
          this.formations = formations;
        },
        (error) => {
          console.error('Failed to fetch formations:', error);
        }
      );
    }
  }

  viewDetails(formationId: number) {
    this.router.navigate(['/organization-formations-refugees', formationId]);
  }

  changeStatus(formationId: number, newStatus: string): void {
    this.apiService.changeFormationStatus(formationId, newStatus).subscribe(
      () => {
        console.log('Status changed successfully');
        // Update the local data as well
        const formation = this.formations.find(f => f.id === formationId);
        if (formation) {
          formation.status = newStatus;
        }
      },
      (error) => {
        console.error('Failed to change status:', error);
      }
    );
  }

  confirmChangeStatus(formationId: number, event: Event): void {
    const target = event.target as HTMLSelectElement;
    if (target) {
      const newStatus = target.value;
      if (confirm('Are you sure you want to change the status?')) {
        this.changeStatus(formationId, newStatus);
      }
    }
  }

}
