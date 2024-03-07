import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-personal-page',
  templateUrl: './personal-page.component.html',
  styleUrls: ['./personal-page.component.css'],
})
export class PersonalPageComponent implements OnInit {
  userDetails: any;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.userDetails = this.authService.getUserDetails();
  }
}
