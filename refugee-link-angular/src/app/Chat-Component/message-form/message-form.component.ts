import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-message-form',
  templateUrl: './message-form.component.html',
  styleUrls: ['./message-form.component.css']
})
export class MessageFormComponent {
  @Output() messageSent = new EventEmitter<string>();
  messageContent = '';

  sendMessage() {
    if (this.messageContent.trim()) {
      this.messageSent.emit(this.messageContent);
      this.messageContent = ''; // Clear the input after sending
    }
  }
}
