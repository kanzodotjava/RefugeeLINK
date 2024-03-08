import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConnectedRefugeesComponent } from './connected-refugees.component';

describe('ConnectedRefugeesComponent', () => {
  let component: ConnectedRefugeesComponent;
  let fixture: ComponentFixture<ConnectedRefugeesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConnectedRefugeesComponent]
    });
    fixture = TestBed.createComponent(ConnectedRefugeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
