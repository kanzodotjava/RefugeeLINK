import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from 'src/app/services/ApiService/api.service';

interface CompletedFormation {
  id: number;
  approved: boolean;
}

@Component({
  selector: 'app-formation-refugees',
  templateUrl: './formation-refugees.component.html',
  styleUrls: ['./formation-refugees.component.css'],
})
export class FormationRefugeesComponent implements OnInit {
  refugees: any[] = [];
  formationId!: number;

  constructor(private apiService: ApiService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    const formationId = this.route.snapshot.paramMap.get('id');
    if (formationId !== null) {
      this.formationId = Number(formationId);
      this.apiService.getRefugeesByFormation(formationId).subscribe(
        (data) => {
          this.refugees = data;
        },
        (error) =>
          console.error('There was an error fetching the refugees', error)
      );
    }
  }

  isFormationApproved(refugee: any, formationId: number): boolean {
    const formation = refugee.completedFormations.find(
      (f: CompletedFormation) => f.id === formationId
    );
    return formation ? formation.approved === true : false;
  }

  approveRefugee(refugeeId: number, formationId: number | null): void {
    if (formationId === null) {
      console.error('Formation ID is null');
      return;
    }

    this.apiService.approveRefugee(refugeeId, formationId).subscribe({
      next: (response) => {
        console.log('Refugee approved', response);
        // Optionally, refresh the list or show a success message
      },
      error: (error) => console.error('Failed to approve refugee', error),
    });
  }
}
