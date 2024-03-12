import { Component, OnInit } from '@angular/core';
import { MessageService } from '../../services/message.service';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  messages: any[] = [];
  currentUser!: string | null; // Now allows null
  receiver: string | null = null; // Initialized as null

  constructor(private messageService: MessageService, private authService: AuthService) { }

  ngOnInit(): void {
    this.currentUser = this.authService.getUsername(); // No type error now
    if (this.currentUser) {
      this.fetchMentorAndConversation();
    } else {
      console.error("No user logged in");
      // Handle not having a logged-in user (e.g., redirect to login page)
    }
  }

  fetchMentorAndConversation(): void {
    if (this.currentUser) {
      this.messageService.getMentorForRefugee(this.currentUser)
        .subscribe(mentor => {
          this.receiver = mentor.username; // Handle this with proper checks
          this.fetchConversation();
        }, error => {
          console.error("Failed to fetch mentor", error);
        });
    }
  }

  fetchConversation(): void {
    if (this.currentUser && this.receiver) {
      this.messageService.getConversation(this.currentUser, this.receiver)
        .subscribe(data => this.messages = data);
    }
  }

  sendMessage(content: string): void {
    if (this.currentUser && this.receiver) {
      this.messageService.sendMessage(this.currentUser, this.receiver, content)
        .subscribe(() => this.fetchConversation()); // Refresh messages after sending
    }
  }
}
