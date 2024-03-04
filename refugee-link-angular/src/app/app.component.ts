import { Component, HostListener } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'refugee-link';
  showNavToggle = false; // Flag to determine whether to show the nav toggle button

  // Method to toggle navigation links
  toggleNav() {
    const navLinks = document.querySelector('.nav-links');
    if (navLinks) {
      navLinks.classList.toggle('show');
    }
  }

  // Listener for window resize events
  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    // Check window width to determine whether to show or hide the nav toggle button
    this.showNavToggle = window.innerWidth <= 768; // Adjust the breakpoint as needed
  }

  
}
