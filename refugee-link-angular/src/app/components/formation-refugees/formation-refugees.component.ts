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


  constructor(private apiService: ApiService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const formationId = this.route.snapshot.paramMap.get('id');
    if (formationId !== null) {
      this.formationId = Number(formationId);
      this.apiService.getRefugeesByFormation(formationId).subscribe(
        (data) => {
          this.refugees = data;
          this.refugees.forEach(refugee => {
            this.apiService.getRefugeeApprovalStatus(refugee.id, this.formationId).subscribe(
              (response) => {
                refugee.approvalStatus = response ? 'Yes' : 'No';
              },
              (error) => console.error('Failed to get refugee approval status', error)
            );
          });
        },
        (error) =>
          console.error('There was an error fetching the refugees', error)
      );
    }
  }


  approveRefugee(refugeeId: number, formationId: number | null): void {
    if (formationId === null) {
      console.error('Formation ID is null');
      return;
    }

    this.apiService.approveRefugee(refugeeId, formationId).subscribe({
      next: (response) => {
        console.log('Refugee approved', response);
        // Refresh the approval status
        this.getRefugeeApprovalStatus(refugeeId, formationId);
      },
      error: (error) => console.error('Failed to approve refugee', error),
    });
  }

  getRefugeeApprovalStatus(refugeeId: number, formationId: number): void {
    this.apiService.getRefugeeApprovalStatus(refugeeId, formationId).subscribe(
      (response) => {
        console.log('Refugee approval status:', response);
        // Find the refugee in the array and update the approval status
        const refugee = this.refugees.find(r => r.id === refugeeId);
        if (refugee) {
          refugee.approvalStatus = response ? 'Yes' : 'No';
        }
      },
      (error) => console.error('Failed to get refugee approval status', error)
    );
  }

  expelRefugeeFromFormation(refugeeId: number, formationId: number): void {
    this.apiService.expelRefugeeFromFormation(refugeeId, formationId).subscribe(
      (response) => {
        console.log('Refugee expelled from formation', response);
        
        this.refugees = this.refugees.filter(r => r.id !== refugeeId);
      },
      (error) => console.error('Failed to expel refugee from formation', error)
    );
  }
}
