import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  private apiUrl = 'http://localhost:8080/api/messages'; 

  constructor(private http: HttpClient) { }

  sendMessage(senderUsername: string, receiverUsername: string, content: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/send`, { senderUsername, receiverUsername, content });
  }

  getConversation(senderUsername: string, receiverUsername: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/conversation`, { params: { senderUsername, receiverUsername } });
  }

  getMentorForRefugee(refugeeUsername: string): Observable<any> {
    return this.http.get(`http://localhost:8080/refugee/by-username/${refugeeUsername}/mentor/username`, { params: { refugeeUsername } });
  }

  getRefugeesForMentor(mentorUsername: string): Observable<any> {
    return this.http.get(`http://localhost:8080/refugee/with-mentor/${mentorUsername}`);
  }

  getMessagesBetweenMentorAndRefugee(mentorUsername: string, refugeeUsername: string): Observable<any> {
    return this.http.get(`http://localhost:8080/api/messages/conversation-between-users/${mentorUsername}/${refugeeUsername}`);
  }
}



