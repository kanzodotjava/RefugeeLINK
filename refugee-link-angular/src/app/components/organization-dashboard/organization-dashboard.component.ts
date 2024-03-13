import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from 'src/app/services/ApiService/api.service';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-organization-dashboard',
  templateUrl: './organization-dashboard.component.html',
  styleUrls: ['./organization-dashboard.component.css'],
})
export class OrganizationDashboardComponent implements OnInit {
  formationForm!: FormGroup;
  organizationId!: number;
  creationSuccess = false;

  constructor(
    private formBuilder: FormBuilder,
    private apiService: ApiService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.initFormationForm();
    this.fetchOrganizationId();
  }

  initFormationForm(): void {
    this.formationForm = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      numberOfLessons: [0, Validators.required],
      startDate: ['', Validators.required],
      duration: [0, Validators.required],
    });
  }

  fetchOrganizationId(): void {
    const username = this.authService.getUsername(); // Assuming you have a getUsername method in your auth service
    if (username) {
      this.apiService.getOrganizationIdByUsername(username).subscribe(
        (response) => {
          this.organizationId = response.id;
        },
        (error) => {
          console.error('Failed to fetch organization ID:', error);
          // Handle error if needed
        }
      );
    } else {
      console.error('Username is null');
      // Handle null username case
    }
  }

  onSubmit(): void {
    if (this.formationForm.valid && this.organizationId) {
      const formData = this.formationForm.value;

      // Parse the ISO date string to a Date object
      const startDate = new Date(formData.startDate);

      // Format the date to 'YYYY-MM-DD' format
      formData.startDate = startDate.toISOString().split('T')[0];

      console.log('Form Data:', formData); // Log form data before sending the request

      // Call your API service method to create the formation
      this.apiService.createFormation(this.organizationId, formData).subscribe(
        (response) => {
          console.log('Formation created:', response);
          this.creationSuccess = true; // Set creation success to true
          // Optionally, you can reset the form after successful submission
          this.formationForm.reset();
        },
        (error) => {
          console.error('Failed to create formation:', error);
          this.creationSuccess = false; // Set creation success to false
          // Handle error if needed
        }
      );
    } else {
      // Mark form controls as touched to display validation errors
      Object.values(this.formationForm.controls).forEach((control) => {
        control.markAsTouched();
      });
    }
  }
}
