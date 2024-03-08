import { Component, OnInit } from '@angular/core';
import { ChatService } from '../../services/chat/chat.service';
import { ChatMessage } from '../../Models/chat-message';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit {

  messageInput: string = '';
  userId: string = "";
  messageList: any[] = [];
  roomId: string = '';

  constructor(private chatService: ChatService,
    private route: ActivatedRoute,
    private Auth: AuthService
  ) {

  }

  ngOnInit(): void {
    const mentorId = this.route.snapshot.paramMap.get('mentorId');
    const userId = this.Auth.getUsername(); // Make sure this cannot return null
    if (mentorId && userId) {
      this.userId = userId; // Set the userId for this component
      this.roomId = this.chatService.createRoomId(userId, mentorId);
      this.chatService.joinRoom(this.roomId);
      this.lisenerMessage(); // Changed the typo from lisenerMessage to listenForMessages
    }
  }

  sendMessage(): void {
    const chatMessage = {
      message: this.messageInput,
      user: this.userId
    } as ChatMessage;

    if (this.roomId) {
      this.chatService.sendMessage(this.roomId, chatMessage);
      this.messageInput = '';
    } else {
      console.error('Cannot send message. No roomId defined.');
      // Handle the error appropriately
    }
  }

  lisenerMessage() : void{
    this.chatService.getMessageSubject().subscribe((messages: any) => {
      this.messageList = messages.map((item: any) => ({
        ...item,
        message_side: item.user === this.userId ? 'sender' : 'receiver'
      }))
    });
  }
  ngOnDestroy(): void {
    this.chatService.disconnect(); // Disconnect from WebSocket when the component is destroyed
  }
}
