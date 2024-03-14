import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { mentorAuthGuard } from './mentor-auth.guard';

describe('mentorAuthGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => mentorAuthGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
