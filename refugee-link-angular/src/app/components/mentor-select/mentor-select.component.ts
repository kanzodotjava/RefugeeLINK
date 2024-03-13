// mentor-select.component.ts

import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/ApiService/api.service';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'mentor-select',
  templateUrl: './mentor-select.component.html',
  styleUrls: ['./mentor-select.component.css'],
})
export class MentorSelectComponent implements OnInit {
  mentors: any[] = [];
  filteredMentors: any[] = [];
  errorMessage: string = '';
  profilePictureUrl: string = './assets/images/pfp/';

  uniqueCountries: string[] = [];
  uniqueProfessions: string[] = [];
  uniquePrimaryLanguage: string[] = [];
  uniqueSecondaryLanguage: string[] = [];
  uniqueNationality: string[] = [];

  constructor(
    private mentorsService: ApiService,
    private router: Router,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.mentorsService.getMentors().subscribe(
      (data) => {
        this.mentors = data.map(mentor => ({ ...mentor, errorMessage: null }));
        this.filteredMentors = [...this.mentors];
        this.initializeFilterOptions();
      },
      (error) => {
        console.error('Error fetching mentors:', error);
        this.errorMessage = 'Failed to load mentors.';
      }
    );
  }

  applyToMentorship(mentorId: number, mentor: any): void {
    const username = localStorage.getItem('username');
    if (username) {
      this.mentorsService.applyToMentorship(username, mentorId).subscribe(
        (response) => {
          console.log('Mentorship application successful:', response);
          mentor.applicationResponse = 'Selected mentor successfully';
          mentor.errorMessage = null;
        },
        (error) => {
          mentor.errorMessage = error.error.message;
          console.error('Error applying to mentorship:', error);
        }
      );
    } else {
      console.error('Username not found in local storage');
      this.errorMessage = 'You must be logged in to apply for mentorship.';
    }
  }


  filterCriteria = {
    country: '',
    profession: '',
    primaryLanguage: '',
    secondaryLanguage: '',
    nationality: '',
  };

  updateFilterCriteria(criteria: any): void {
    this.filterCriteria = { ...this.filterCriteria, ...criteria };
    this.filterMentors();
  }

  filterMentors(): void {
    this.filteredMentors = this.mentors.filter(mentor => {
      return (
        (this.filterCriteria.country ? mentor.country === this.filterCriteria.country : true) &&
        (this.filterCriteria.profession ? mentor.profession === this.filterCriteria.profession : true) &&
        (this.filterCriteria.primaryLanguage ? mentor.primaryLanguage === this.filterCriteria.primaryLanguage : true) &&
        (this.filterCriteria.secondaryLanguage ? mentor.secondaryLanguage === this.filterCriteria.secondaryLanguage : true) &&
        (this.filterCriteria.nationality ? mentor.nationality === this.filterCriteria.nationality : true)
      );
    });
  }

  initializeFilterOptions(): void {
    this.uniqueCountries = this.extractUniqueValues(this.mentors, 'country');
    this.uniqueProfessions = this.extractUniqueValues(this.mentors, 'profession');
    this.uniquePrimaryLanguage = this.extractUniqueValues(this.mentors, 'primaryLanguage');
    this.uniqueSecondaryLanguage = this.extractUniqueValues(this.mentors, 'secondaryLanguage');
    this.uniqueNationality = this.extractUniqueValues(this.mentors, 'nationality');


  }

  extractUniqueValues(items: any[], key: string): string[] {
    return [...new Set(items.map(item => item[key]))].sort();
  }

  applyFilters(): void {
    this.filterMentors();
  }
  
}
