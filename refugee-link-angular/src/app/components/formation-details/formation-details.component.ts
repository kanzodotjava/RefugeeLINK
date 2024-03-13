import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from 'src/app/services/ApiService/api.service';

@Component({
  selector: 'app-formation-details',
  templateUrl: './formation-details.component.html',
  styleUrls: ['./formation-details.component.css']
})
export class FormationDetailsComponent implements OnInit {
  formationId!: number;
  formationDetails: any;

  constructor(
    private route: ActivatedRoute,
    private apiService: ApiService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.formationId = params['id'];
      this.fetchFormationDetails();
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
}
