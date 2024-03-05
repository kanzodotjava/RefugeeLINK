import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent {

  constructor(private router: Router) { }

  navigateToRegisterAsMentor() {
    this.router.navigate(['/register-as-mentor']);
  }
}
