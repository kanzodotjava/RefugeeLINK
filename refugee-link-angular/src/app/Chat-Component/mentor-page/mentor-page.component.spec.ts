import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MentorPageComponent } from './mentor-page.component';

describe('MentorPageComponent', () => {
  let component: MentorPageComponent;
  let fixture: ComponentFixture<MentorPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MentorPageComponent]
    });
    fixture = TestBed.createComponent(MentorPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
