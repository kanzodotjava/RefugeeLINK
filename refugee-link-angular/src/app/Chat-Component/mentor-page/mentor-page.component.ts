import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Subscription, interval } from 'rxjs';
import { AuthService } from 'src/app/services/auth/auth.service';
import { MessageService } from 'src/app/services/message.service';

@Component({
  selector: 'app-mentor-page',
  templateUrl: './mentor-page.component.html',
  styleUrls: ['./mentor-page.component.css']
})
export class MentorPageComponent implements OnInit {
  refugees: any[] = [];
  selectedRefugeeUsername!: string;
  messages: any[] = [];
  profilePictureUrl: string = './assets/images/pfp/';
  @ViewChild('scrollMe') private myScrollContainer!: ElementRef;
  subscription: Subscription;
  source = interval(5000);

  constructor(
    private authService: AuthService,
    private messageService: MessageService
  ) {
    this.subscription = this.source.subscribe(val => this.refreshMessages());

  }

  ngOnInit(): void {
    const mentorUsername = this.authService.getUsername();
    if (mentorUsername) {
      this.messageService.getRefugeesForMentor(mentorUsername).subscribe(data => {
        this.refugees = data;
      });
    }
  }

  onSelectRefugee(refugeeUsername: string): void {
    const mentorUsername = this.authService.getUsername();
    if (mentorUsername) {
      this.selectedRefugeeUsername = refugeeUsername;
      this.messageService.getMessagesBetweenMentorAndRefugee(mentorUsername, refugeeUsername).subscribe(data => {
        this.messages = data;
      });
    }
  }
  
  sendMessage(content: string): void {
    const mentorUsername = this.authService.getUsername();
    if (mentorUsername && this.selectedRefugeeUsername) {
      this.messageService.sendMessage(mentorUsername, this.selectedRefugeeUsername, content)
        .subscribe(() => {
          // Optionally, refresh the conversation list after sending a message
          this.onSelectRefugee(this.selectedRefugeeUsername);
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
    console.log("refreshing")
    const mentorUsername = this.authService.getUsername();
    const refugeeUsername = this.selectedRefugeeUsername;
    if (refugeeUsername && mentorUsername) {
      this.messageService.getMessagesBetweenMentorAndRefugee(refugeeUsername, mentorUsername)
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

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
