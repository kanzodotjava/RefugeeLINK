import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MentorSelectComponent } from './mentor-select.component';

describe('MentorSelectComponent', () => {
  let component: MentorSelectComponent;
  let fixture: ComponentFixture<MentorSelectComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MentorSelectComponent]
    });
    fixture = TestBed.createComponent(MentorSelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
