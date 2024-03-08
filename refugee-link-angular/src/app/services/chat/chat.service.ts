import { Injectable } from '@angular/core';
import * as SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';
import { ChatMessage } from '../../Models/chat-message';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private stompClient!: Client;
  private messageSubject: BehaviorSubject<ChatMessage[]> = new BehaviorSubject<ChatMessage[]>([]);

  constructor() {
    this.initConnectionSocket();
  }

  initConnectionSocket() {
    const url = '//localhost:3000/chat-socket';
    const socket = new SockJS(url);

    this.stompClient = new Client({
      brokerURL: socket.url,
      webSocketFactory: () => {
        return socket; // Return the SockJS instance
      },
      onConnect: () => {
        console.log('Connected');
        // You can set up subscriptions here
      }
    });

    this.stompClient.activate();
  }
  createRoomId(userId: string, mentorId: string): string {
    return `chat_${userId}_${mentorId}`;
  }

  joinRoom(roomId: string) {
    if (!this.stompClient.active) {
      this.stompClient.activate();
    }

    this.stompClient.onConnect = () => {
      this.stompClient.subscribe(`/topic/${roomId}`, (message: any) => {
        const messageContent: ChatMessage = JSON.parse(message.body);
        this.messageSubject.next([...this.messageSubject.getValue(), messageContent]);
      });
    };
  }

  sendMessage(roomId: string, chatMessage: ChatMessage) {
    this.stompClient.publish({
      destination: `/app/chat/${roomId}`,
      body: JSON.stringify(chatMessage),
    });
  }

  getMessageSubject() {
    return this.messageSubject.asObservable();
  }

  disconnect() {
    if (this.stompClient.active) {
      this.stompClient.deactivate();
    }
  }
}
