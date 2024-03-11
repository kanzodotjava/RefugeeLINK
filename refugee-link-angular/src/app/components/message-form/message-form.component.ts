// src/app/components/message-form/message-form.component.ts
import { Component } from '@angular/core';
import { Message } from '../../models/message.model';
import { MessageService } from '../../services/message.service';

@Component({
  selector: 'app-message-form',
  templateUrl: './message-form.component.html',
  styleUrls: ['./message-form.component.css']
})
export class MessageFormComponent {
  newMessage: Message = {
    senderUsername: '', // Initialize with the sender's username
    receiverUsername: '', // Initialize with the receiver's username
    content: ''
  };

  constructor(private messageService: MessageService) {}

  sendMessage(): void {
    if (this.newMessage.content.trim()) {
      this.messageService.sendMessage(this.newMessage).subscribe({
        next: (message) => {
          console.log('Message sent:', message);
          this.newMessage.content = ''; // Clear the input after sending
        },
        error: (error) => {
          console.error('Error sending message:', error);
        }
      });
    }
  }
}
