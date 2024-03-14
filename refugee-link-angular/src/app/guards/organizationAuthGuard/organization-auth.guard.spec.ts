import { TestBed } from '@angular/core/testing';
import { CanActivate, Router } from '@angular/router';
import { OrganizationAuthGuard } from './organization-auth.guard';
import { AuthService } from 'src/app/services/auth/auth.service';

describe('OrganizationAuthGuard', () => {
  let guard: OrganizationAuthGuard;
  let authService: AuthService;
  let router: Router;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [OrganizationAuthGuard, AuthService, Router],
    });
    guard = TestBed.inject(OrganizationAuthGuard);
    authService = TestBed.inject(AuthService);
    router = TestBed.inject(Router);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });

  it('should allow access for organization user', () => {
    spyOn(authService, 'getUserType').and.returnValue('organization');
    spyOn(router, 'navigate');

    expect(guard.canActivate()).toBeTrue();
    expect(router.navigate).not.toHaveBeenCalled();
  });

  it('should disallow access for non-organization user', () => {
    spyOn(authService, 'getUserType').and.returnValue('user');
    spyOn(router, 'navigate');

    expect(guard.canActivate()).toBeFalse();
    expect(router.navigate).toHaveBeenCalledOnceWith(['/login']);
  });
});
