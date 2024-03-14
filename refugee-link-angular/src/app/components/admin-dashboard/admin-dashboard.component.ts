import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/ApiService/api.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css'],
})
export class AdminDashboardComponent implements OnInit {
  mentors: any[] = [];
  organizationForm!: FormGroup;
  organizations: any[] = [];
  editingOrganization: any = null;

  constructor(private apiService: ApiService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.fetchMentors();
    this.initForm();
    this.fetchOrganizations();
  }

  fetchMentors() {
    this.apiService.getAllMentors().subscribe((mentors: any[]) => {
      this.mentors = mentors;
    });
  }

  acceptMentor(mentorId: number) {
    this.apiService.acceptMentor(mentorId).subscribe(() => {
      this.fetchMentors(); // Refresh the mentors list
    });
  }

  rejectMentor(mentorId: number) {
    this.apiService.rejectMentor(mentorId).subscribe(() => {
      this.fetchMentors(); // Refresh the mentors list
    });
  }
  initForm(): void {
    this.organizationForm = this.fb.group({
      name: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit(): void {
    if (this.organizationForm.valid) {
      const formData = this.organizationForm.value;
      if (this.editingOrganization) {
        // If editing an existing organization, update it
        formData.id = this.editingOrganization.id;
        this.apiService.updateOrganization(formData).subscribe(
          (response) => {
            console.log('Organization updated:', response);
            this.fetchOrganizations(); // Refresh organization list
            this.resetForm();
          },
          (error) => {
            console.error('Failed to update organization:', error);
          }
        );
      } else {
        // If creating a new organization, add it
        this.apiService.sendFormDataOrganization(formData).subscribe(
          (response) => {
            console.log('Organization added:', response);
            this.fetchOrganizations(); // Refresh organization list
            this.resetForm();
          },
          (error) => {
            console.error('Failed to add organization:', error);
          }
        );
      }
    } else {
      // Mark form controls as touched to display validation errors
      this.markFormGroupTouched(this.organizationForm);
    }
  }

  resetForm(): void {
    this.organizationForm.reset();
    this.editingOrganization = null;
  }

  markFormGroupTouched(formGroup: FormGroup): void {
    Object.values(formGroup.controls).forEach((control) => {
      control.markAsTouched();

      if (control instanceof FormGroup) {
        this.markFormGroupTouched(control);
      }
    });
  }

  fetchOrganizations() {
    this.apiService.getOrganizations().subscribe((organizations: any[]) => {
      this.organizations = organizations;
    });
  }

  deleteOrganization(id: number) {
    if (confirm('Are you sure you want to delete this organization?')) {
      this.apiService.deleteOrganization(id).subscribe(() => {
        // If deletion is successful, refresh the organization list
        this.fetchOrganizations();
      });
    }
  }

  editOrganization(organization: any): void {
    this.editingOrganization = organization;
    // Set form values to the organization being edited
    this.organizationForm.patchValue({
      name: organization.name,
      username: organization.username,
      password: organization.password,
    });
  }
}
