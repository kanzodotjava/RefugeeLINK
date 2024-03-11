// src/app/services/chat.service.ts

import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private selectedCounterpartUsernameSource = new BehaviorSubject<string | null>(null);
  selectedCounterpartUsername$ = this.selectedCounterpartUsernameSource.asObservable();

  constructor() { }

  selectCounterpartUsername(username: string) {
    this.selectedCounterpartUsernameSource.next(username);
  }
}
