import { AfterViewChecked, Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription, interval } from 'rxjs';
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
  subscription: Subscription;
  source = interval(5000);

  constructor(private messageService: MessageService, 
    private authService: AuthService,
    private router: Router) {
    this.subscription = this.source.subscribe(val => this.refreshMessages());

    
  }

  ngOnInit(): void {
    const refugeeUsername = this.authService.getUsername();
    if (refugeeUsername) {
      this.messageService.getMentorForRefugee(refugeeUsername).subscribe(data => {
        
        this.selectedMentorUsername = data.username;
  
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
    console.log("refreshing")
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
    this.router.navigate(['/mentor-select']);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}

