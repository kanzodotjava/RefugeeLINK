import { TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';
import { RefugeeAuthGuard } from './refugee-auth.guard';

describe('RefugeeAuthGuard', () => {
  let guard: RefugeeAuthGuard;
  let authService: jasmine.SpyObj<AuthService>;
  let router: jasmine.SpyObj<Router>;

  beforeEach(() => {
    const authServiceSpy = jasmine.createSpyObj('AuthService', ['getUserType']);
    const routerSpy = jasmine.createSpyObj('Router', ['navigate']);

    TestBed.configureTestingModule({
      providers: [
        RefugeeAuthGuard,
        { provide: AuthService, useValue: authServiceSpy },
        { provide: Router, useValue: routerSpy }
      ]
    });

    guard = TestBed.inject(RefugeeAuthGuard);
    authService = TestBed.inject(AuthService) as jasmine.SpyObj<AuthService>;
    router = TestBed.inject(Router) as jasmine.SpyObj<Router>;
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });

  it('should allow activation for refugee user', () => {
    authService.getUserType.and.returnValue('refugee');

    const canActivate = guard.canActivate();
    
    expect(canActivate).toBe(true);
  });

  it('should navigate to login for non-refugee user', () => {
    authService.getUserType.and.returnValue('organization');
    router.navigate.and.returnValue(Promise.resolve(true)); // Mock navigate to return a resolved Promise

    const canActivate = guard.canActivate();
    
    expect(router.navigate).toHaveBeenCalledWith(['/login']);
    expect(canActivate).toBe(false);
  });
});
