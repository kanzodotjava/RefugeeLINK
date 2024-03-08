import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConnectedMentorComponent } from './connected-mentor.component';

describe('ConnectedMentorComponent', () => {
  let component: ConnectedMentorComponent;
  let fixture: ComponentFixture<ConnectedMentorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConnectedMentorComponent]
    });
    fixture = TestBed.createComponent(ConnectedMentorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
