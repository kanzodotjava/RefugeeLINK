import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/ApiService/api.service';

@Component({
  selector: 'mentor-select',
  templateUrl: './mentor-select.component.html',
  styleUrls: ['./mentor-select.component.css']
})
export class MentorSelectComponent implements OnInit {

  mentors: any[] = [];

  constructor(private mentorsService: ApiService) { }

  ngOnInit(): void {
    this.mentorsService.getMentors().subscribe((data) => {
      this.mentors = data;
    });
  }

}
