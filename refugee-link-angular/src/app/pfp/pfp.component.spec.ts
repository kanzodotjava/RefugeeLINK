import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PfpComponent } from './pfp.component';

describe('PfpComponent', () => {
  let component: PfpComponent;
  let fixture: ComponentFixture<PfpComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PfpComponent]
    });
    fixture = TestBed.createComponent(PfpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
