import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterAsRefugeeComponent } from './register-as-refugee.component';

describe('RegisterAsRefugeeComponent', () => {
  let component: RegisterAsRefugeeComponent;
  let fixture: ComponentFixture<RegisterAsRefugeeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegisterAsRefugeeComponent]
    });
    fixture = TestBed.createComponent(RegisterAsRefugeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
