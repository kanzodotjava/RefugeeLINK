import { Injectable } from '@angular/core';
import { Client, IMessage } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private serverUrl = 'http://localhost:8080/chat-socket';
  public messages: BehaviorSubject<string> = new BehaviorSubject('');
  private client: Client;

  constructor() {
    this.client = new Client();
    this.client.webSocketFactory = () => {
      // Bypass type by returning any.
      return new SockJS(this.serverUrl) as any;
    };
  }

  initializeWebSocketConnection(username: string, counterpartUsername: string): void {
    this.client.onConnect = () => {
      console.log('WebSocket Connected');
      // Use method parameters directly instead of non-existent properties
      this.client.subscribe(`/topic/chat/${username}/${counterpartUsername}`, (message: IMessage) => {
        console.log('Message received:', message);
        if (message.body) {
          this.messages.next(message.body);
        }
      });
      this.client.activate();
    };
  }

  sendMessage(username: string, counterpartUsername: string, message: string): void {
    this.client.publish({ destination: `/app/chat/${username}/${counterpartUsername}`, body: message });
  }
}


