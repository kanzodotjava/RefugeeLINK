import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { WebSocketService } from '../../services/web-socket.service';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  newMessage: string = '';
  messageList: string[] = [];
  counterpartUsername: string | null = '';

  constructor(
    private route: ActivatedRoute,
    private webSocketService: WebSocketService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.counterpartUsername = params['counterpartUsername'];
      // Fetch the current user's username from AuthService
      const username = this.authService.getUsername();

      if (username && this.counterpartUsername) {
        // Correctly establish the WebSocket connection
        this.webSocketService.initializeWebSocketConnection(username, this.counterpartUsername);
      } else {
        console.error("Username or counterpartUsername missing.");
      }
    });
  }

  sendMessage(): void {
    const username = this.authService.getUsername();
    if (this.newMessage.trim() !== '' && username && this.counterpartUsername) {
      this.webSocketService.sendMessage(username, this.counterpartUsername, this.newMessage);
      this.newMessage = ''; // Clear the message input after sending
    }
  }
}
