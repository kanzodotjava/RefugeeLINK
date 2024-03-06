import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class NewsService {
  apiKey = 'b5335f83af7a46ad99f2bf5945ed1629';

  constructor(private http: HttpClient) {}

  getNews(): Observable<any> {
    const apiUrl = `https://newsapi.org/v2/everything?q=refugees&apiKey=${this.apiKey}`;
    return this.http.get<any>(apiUrl);
  }
}
