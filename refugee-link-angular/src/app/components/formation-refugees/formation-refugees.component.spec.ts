import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormationRefugeesComponent } from './formation-refugees.component';

describe('FormationRefugeesComponent', () => {
  let component: FormationRefugeesComponent;
  let fixture: ComponentFixture<FormationRefugeesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormationRefugeesComponent]
    });
    fixture = TestBed.createComponent(FormationRefugeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
