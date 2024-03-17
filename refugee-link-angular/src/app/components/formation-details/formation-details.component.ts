import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from 'src/app/services/ApiService/api.service';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-formation-details',
  templateUrl: './formation-details.component.html',
  styleUrls: ['./formation-details.component.css']
})
export class FormationDetailsComponent implements OnInit {
  formationId!: number;
  formationDetails: any;
  username: string | null;
  isApproved: boolean | null = null;

  constructor(
    private route: ActivatedRoute,
    private apiService: ApiService,
    private authService: AuthService
  ) { this.username = this.authService.getUsername(); }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.formationId = params['id'];
      this.fetchFormationDetails();
      this.getIsApproed(this.username!, this.formationId);

    });
  }

  fetchFormationDetails(): void {
    this.apiService.getFormationDetails(this.formationId).subscribe(
      (data) => {
        this.formationDetails = data;
      },
      (error) => {
        console.error('Error fetching formation details:', error);
      }
    );
  }

  getIsApproed(username: string, formationId: number): void {
    this.apiService.getRefugeeIdByUsername(username).subscribe(
      (id) => {
        this.apiService.getRefugeeApprovalStatus(id, formationId).subscribe(
          (status) => {
            this.isApproved = status; // Store the status in the variable
          },
          (error) => {
            console.error('Failed to fetch refugee approval status:', error);
          }
        );
      },
      (error) => {
        console.error('Failed to fetch refugee id:', error);
      }
    );
  }
}
