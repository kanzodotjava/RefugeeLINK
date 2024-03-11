
import { Component, OnInit, Input } from '@angular/core';
import { MessageService } from '../../services/message.service';
import { Message } from '../../models/message.model';

@Component({
  selector: 'app-message-list',
  templateUrl: './message-list.component.html',
  styleUrls: ['./message-list.component.css']
})
export class MessageListComponent implements OnInit {
  @Input() senderUsername!: string;
  @Input() receiverUsername!: string;
  messages: Message[] = [];

  constructor(private messageService: MessageService) {}

  ngOnInit(): void {
    this.loadConversation();
  }

  loadConversation(): void {
    if (this.senderUsername && this.receiverUsername) {
      this.messageService.getConversation(this.senderUsername, this.receiverUsername).subscribe({
        next: (messages) => {
          this.messages = messages;
        },
        error: (error) => {
          console.error('Error loading messages:', error);
        }
      });
    }
  }
}
