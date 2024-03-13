import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganizationLoginComponent } from './organization-login.component';

describe('OrganizationLoginComponent', () => {
  let component: OrganizationLoginComponent;
  let fixture: ComponentFixture<OrganizationLoginComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OrganizationLoginComponent]
    });
    fixture = TestBed.createComponent(OrganizationLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
