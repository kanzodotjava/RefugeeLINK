import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Message } from 'src/app/models/message.model';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  private apiUrl = 'http://localhost:8080/api/messages'; // URL to web API

  constructor(private http: HttpClient) {}

  sendMessage(message: Message): Observable<Message> {
    return this.http.post<Message>(`${this.apiUrl}/send`, message);
  }

  getConversation(senderUsername: string, receiverUsername: string): Observable<Message[]> {
    const params = new HttpParams()
      .set('senderUsername', senderUsername)
      .set('receiverUsername', receiverUsername);

    return this.http.get<Message[]>(`${this.apiUrl}/conversation`, { params });
  }
}