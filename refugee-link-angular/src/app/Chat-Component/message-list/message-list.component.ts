import { AfterViewChecked, Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';
import { MessageService } from 'src/app/services/message.service';

@Component({
  selector: 'app-message-list',
  templateUrl: './message-list.component.html',
  styleUrls: ['./message-list.component.css']
})
export class MessageListComponent implements OnInit, AfterViewChecked{
  selectedMentorUsername!: string;
  messages: any[] = [];
  @ViewChild('scrollMe') private myScrollContainer!: ElementRef;


  constructor(private messageService: MessageService, 
    private authService: AuthService,
  private router: Router) {
    
  }

  ngOnInit(): void {
    const refugeeUsername = this.authService.getUsername();
    if (refugeeUsername) {
      this.messageService.getMentorForRefugee(refugeeUsername).subscribe(data => {
        // Assuming 'data' contains the mentor's username directly
        // If it's wrapped inside an object, you might need something like data.username
        this.selectedMentorUsername = data.username; // Adjust according to your API response structure
  
        // Once the mentor's username is set, fetch the messages
        this.messageService.getMessagesBetweenMentorAndRefugee(this.selectedMentorUsername, refugeeUsername).subscribe(messages => {
          this.messages = messages;
        });
      });
    }
  }

  onSelectMentor(mentorUsername: string): void {
    const refugeeUsername = this.authService.getUsername();
    if (refugeeUsername) {
      this.selectedMentorUsername = mentorUsername;
      this.messageService.getMessagesBetweenMentorAndRefugee(mentorUsername, refugeeUsername).subscribe(data => {
        this.messages = data;
      });
    }
  }

  sendMessage(content: string): void {
    const refugeeUsername = this.authService.getUsername();
    if (refugeeUsername && this.selectedMentorUsername) {
      this.messageService.sendMessage(refugeeUsername, this.selectedMentorUsername, content)
        .subscribe(() => {
          // Optionally, refresh the conversation list after sending a message
          this.onSelectMentor(this.selectedMentorUsername);
        }, error => {
          console.error("Failed to send message", error);
          // Handle sending error
        });
    }
  }

  ngAfterViewChecked(): void {
    this.scrollToBottom();
  }

  scrollToBottom(): void {
    try {
      this.myScrollContainer.nativeElement.scrollTop = this.myScrollContainer.nativeElement.scrollHeight;
    } catch(err) { }
  }

  refreshMessages(): void {
    const mentorUsername = this.selectedMentorUsername;
    const refugeeUsername = this.authService.getUsername();
    if (mentorUsername && refugeeUsername) {
      this.messageService.getMessagesBetweenMentorAndRefugee(mentorUsername, refugeeUsername)
        .subscribe(
          messages => {
            this.messages = messages;
            this.scrollToBottom();
          },
          error => {
            console.error("Error refreshing messages", error);
            // Handle the error properly
          }
        );
    }
  }

  goToMentorSelect(): void {
    this.router.navigate(['/mentor-select']); // Adjust the route as per your application's routing
  }
}

