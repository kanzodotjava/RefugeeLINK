import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ApiService } from '../../services/ApiService/api.service';

@Component({
  selector: 'app-register-as-refugee',
  templateUrl: './register-as-refugee.component.html',
  styleUrls: ['./register-as-refugee.component.css'],
})
export class RegisterAsRefugeeComponent implements OnInit {
  refugeeForm!: FormGroup;
  isRefugeeNumberValid: boolean = true;

  constructor(private fb: FormBuilder, private apiService: ApiService) {}

  ngOnInit() {
    this.refugeeForm = this.fb.group({
      id: [0],
      name: ['RefugeeName'],
      emailAddress: ['RefugeeName@gmail.com'],
      phoneNumber: ['123'],
      country: ['USA'],
      userName: ['123'],
      password: ['123'],
      nationality: ['USA'],
      primaryLanguage: ['ENGLISH'],
      secondaryLanguage: ['ENGLISH'],
      citizenCard: [123],
      refugeeNumber: ['111111'],
      status: ['AWAITING'],
      birthdayDate: ['2024-03-05T09:46:45.621Z'],
    });
  }

  onSubmit() {
    // Check form validity
    if (this.refugeeForm.valid) {
      const refugeeNumber = this.refugeeForm.get('refugeeNumber')?.value || '';

      // First, check if the refugee number is valid
      this.apiService.checkRefugeeNumberExists(refugeeNumber).subscribe(
        (isValid) => {
          if (isValid) {
            this.apiService.sendFormData(this.refugeeForm.value).subscribe(
              (response) => {
                console.log('Form submission successful', response);
                // Handle successful form submission here
              },
              (error) => {
                console.error('Form submission error', error);
                // Handle form submission error here
              }
            );
          } else {
            // Set the flag to false to show the error message
            this.isRefugeeNumberValid = false;
          }
        },
        (error) => {
          console.error('Error checking refugee number', error);
        }
      );
    } else {
      console.log('Form is not valid');
    }
  }
}
