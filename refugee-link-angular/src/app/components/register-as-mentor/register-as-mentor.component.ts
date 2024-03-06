import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-register-as-mentor',
  templateUrl: './register-as-mentor.component.html',
  styleUrls: ['./register-as-mentor.component.css'],
})
export class RegisterAsMentorComponent {
  professions = [
    'Engineer',
    'Doctor',
    'Teacher',
    'Artist',
    'Lawyer',
    'Chef',
    'Software Developer',
    'Entrepreneur',
    'Writer',
    'Musician',
    'Nurse',
    'Architect',
    'Accountant',
    'Police Officer',
    'Firefighter',
    'Dentist',
    'Pharmacist',
    'Psychologist',
    'Electrician',
    'Plumber',
    'Mechanic',
    'Pilot',
    'Farmer',
    'Journalist',
    'Graphic Designer',
    'Marketing Manager',
    'Physiotherapist',
    'Real Estate Agent',
    'Barista',
    'Fitness Trainer',
    'Flight Attendant',
    'Actor',
    'Banker',
    'Librarian',
    'Military Officer',
    'Paramedic',
    'Social Worker',
    'Veterinarian',
    'Hairdresser',
    'Taxi Driver',
    'Waiter',
    'Tour Guide',
    'Scientist',
    'Consultant',
    'Photographer',
    'IT Technician',
    'Event Planner',
    'Secretary',
    'Carpenter',
    'Actuary',
    'Astrologer',
    'Brewer',
    'Chiropractor',
    'Dietitian',
    'Geologist',
    'Historian',
    'Judge',
    'Lifeguard',
    'Mortician',
    'Notary',
    'Optometrist',
    'Paralegal',
    'Radar Controller',
    'Zookeeper',
    'Underwater Welder',
    'Bank Teller',
    'Beautician',
    'Brewmaster',
    'Cartographer',
    'Choreographer',
    'Composer',
    'Computer Programmer',
    'Counselor',
    'Criminal Investigator',
    'Criminologist',
    'Data Analyst',
    'Database Administrator',
    'Demonologist',
    'Economist',
    'Editor',
    'Embalmer',
    'Ethnologist',
    'Forensic Scientist',
    'Gamer',
    'Gardener',
    'Gemologist',
    'Geneticist',
    'Glassblower',
    'Hairstylist',
    'Home Inspector',
    'Horse Trainer',
    'Interior Designer',
    'Jeweler',
    'Linguist',
    'Magistrate',
    'Makeup Artist',
    'Marine Biologist',
    'Medical Transcriptionist',
    'Meteorologist',
    'Microbiologist',
    'Museum Curator',
    'Mycologist',
    'Nuclear Physicist',
    'Numismatist',
    'Oceanographer',
    'Ornithologist',
    'Parasitologist',
    'Pathologist',
    'Paleontologist',
    'Peace Officer',
    'Penny Stock Trader',
    'Performing Artist',
    'Petroleum Engineer',
    'Phlebotomist',
    'Photojournalist',
    'Physician Assistant',
    'Political Scientist',
    'Postmaster',
    'Psychiatrist',
    'Public Relations Specialist',
    'Quantum Physicist',
    'Radiation Therapist',
    'Radiologist',
    'Seismologist',
    'Sheet Metal Worker',
    'Solar Engineer',
    'Statistician',
    'Taxidermist',
    'Tattoo Artist',
    'Telecommunications Technician',
    'Urban Planner',
    'Virologist',
    'Volcanologist',
    'Wildlife Biologist',
    'Wind Turbine Technician',
    'Other',
  ];

  mentorForm: FormGroup;
  errorMessage: string | null = null;

  constructor(
    private httpClient: HttpClient,
    private formBuilder: FormBuilder
  ) {
    this.mentorForm = this.formBuilder.group({
      id: [0],
      name: ['TESTE', Validators.required],
      emailAddress: [
        'TESTE@GMAIL.COM',
        [Validators.required, Validators.email],
      ],
      phoneNumber: ['123123'],
      country: ['Portugal'],
      userName: ['MentorTESTE', Validators.required],
      password: ['123123', Validators.required],
      nationality: ['Portugal'],
      primaryLanguage: ['Portugal'],
      secondaryLanguage: ['Portugal'],
      citizenCard: ['123123'],
      profession: ['POSTMASTER'],
      description: ['TESTEDECRIPTION'],
      birthdayDate: ['29-03-2024'],
    });
  }

  onSubmit() {
    if (this.mentorForm.valid) {
      this.httpClient
        .post('http://localhost:8080/mentor', this.mentorForm.value)
        .subscribe(
          (response) => {
            console.log('Mentor registration successful:', response);
            // Optionally, redirect the user or show a success message
          },
          (error) => {
            console.error('Error registering mentor:', error);
            if (error.error) {
              this.errorMessage = error.error;
            } else {
              this.errorMessage = 'An error occurred while registering mentor.';
            }
          }
        );
    } else {
      // Form is invalid, handle validation errors
    }
  }
}
