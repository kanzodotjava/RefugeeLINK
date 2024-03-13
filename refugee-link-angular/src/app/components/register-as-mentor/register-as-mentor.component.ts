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
    'ENGINEER',
    'DOCTOR',
    'TEACHER',
    'ARTIST',
    'LAWYER',
    'CHEF',
    'SOFTWARE DEVELOPER',
    'ENTREPRENEUR',
    'WRITER',
    'MUSICIAN',
    'NURSE',
    'ARCHITECT',
    'ACCOUNTANT',
    'POLICE OFFICER',
    'FIREFIGHTER',
    'DENTIST',
    'PHARMACIST',
    'PSYCHOLOGIST',
    'ELECTRICIAN',
    'PLUMBER',
    'MECHANIC',
    'PILOT',
    'FARMER',
    'JOURNALIST',
    'GRAPHIC DESIGNER',
    'MARKETING MANAGER',
    'PHYSIOTHERAPIST',
    'REAL ESTATE AGENT',
    'BARISTA',
    'FITNESS TRAINER',
    'FLIGHT ATTENDANT',
    'ACTOR',
    'BANKER',
    'LIBRARIAN',
    'MILITARY OFFICER',
    'PARAMEDIC',
    'SOCIAL WORKER',
    'VETERINARIAN',
    'HAIRDRESSER',
    'TAXI DRIVER',
    'WAITER',
    'TOUR GUIDE',
    'SCIENTIST',
    'CONSULTANT',
    'PHOTOGRAPHER',
    'IT TECHNICIAN',
    'EVENT PLANNER',
    'SECRETARY',
    'CARPENTER',
    'ACTUARY',
    'ASTROLOGER',
    'BREWER',
    'CHIROPRACTOR',
    'DIETITIAN',
    'GEOLOGIST',
    'HISTORIAN',
    'JUDGE',
    'LIFEGUARD',
    'MORTICIAN',
    'NOTARY',
    'OPTOMETRIST',
    'PARALEGAL',
    'RADAR CONTROLLER',
    'ZOOKEEPER',
    'UNDERWATER WELDER',
    'BANK TELLER',
    'BEAUTICIAN',
    'BREWMASTER',
    'CARTOGRAPHER',
    'CHOREOGRAPHER',
    'COMPOSER',
    'COMPUTER PROGRAMMER',
    'COUNSELOR',
    'CRIMINAL INVESTIGATOR',
    'CRIMINOLOGIST',
    'DATA ANALYST',
    'DATABASE ADMINISTRATOR',
    'DEMONOLOGIST',
    'ECONOMIST',
    'EDITOR',
    'EMBALMER',
    'ETHNOLOGIST',
    'FORENSIC SCIENTIST',
    'GAMER',
    'GARDENER',
    'GEMOLOGIST',
    'GENETICIST',
    'GLASSBLOWER',
    'HAIRSTYLIST',
    'HOME INSPECTOR',
    'HORSE TRAINER',
    'INTERIOR DESIGNER',
    'JEWELER',
    'LINGUIST',
    'MAGISTRATE',
    'MAKEUP ARTIST',
    'MARINE BIOLOGIST',
    'MEDICAL TRANSCRIPTIONIST',
    'METEOROLOGIST',
    'MICROBIOLOGIST',
    'MUSEUM CURATOR',
    'MYCOLOGIST',
    'NUCLEAR PHYSICIST',
    'NUMISMATIST',
    'OCEANOGRAPHER',
    'ORNITHOLOGIST',
    'PARASITOLOGIST',
    'PATHOLOGIST',
    'PALEONTOLOGIST',
    'PEACE OFFICER',
    'PENNY STOCK TRADER',
    'PERFORMING ARTIST',
    'PETROLEUM ENGINEER',
    'PHLEBOTOMIST',
    'PHOTOJOURNALIST',
    'PHYSICIAN ASSISTANT',
    'POLITICAL SCIENTIST',
    'POSTMASTER',
    'PSYCHIATRIST',
    'PUBLIC RELATIONS SPECIALIST',
    'QUANTUM PHYSICIST',
    'RADIATION THERAPIST',
    'RADIOLOGIST',
    'SEISMOLOGIST',
    'SHEET METAL WORKER',
    'SOLAR ENGINEER',
    'STATISTICIAN',
    'TAXIDERMIST',
    'TATTOO ARTIST',
    'TELECOMMUNICATIONS TECHNICIAN',
    'URBAN PLANNER',
    'VIROLOGIST',
    'VOLCANOLOGIST',
    'WILDLIFE BIOLOGIST',
    'WIND TURBINE TECHNICIAN',
    'OTHER',
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
