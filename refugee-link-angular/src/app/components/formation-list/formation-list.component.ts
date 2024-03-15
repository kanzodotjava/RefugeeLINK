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
}
