import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RefugeeFormationsComponent } from './refugee-formations.component';

describe('RefugeeFormationsComponent', () => {
  let component: RefugeeFormationsComponent;
  let fixture: ComponentFixture<RefugeeFormationsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RefugeeFormationsComponent]
    });
    fixture = TestBed.createComponent(RefugeeFormationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
