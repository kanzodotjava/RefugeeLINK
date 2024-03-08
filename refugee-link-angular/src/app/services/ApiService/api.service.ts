import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private baseUrl = 'http://localhost:8080';
  private loginUrl = 'https://localhost:7165/login';

  constructor(private http: HttpClient) {}

  sendFormData(data: any): Observable<any> {
    const endpointUrl = 'http://localhost:8080/refugee';
    return this.http.post(endpointUrl, data);
  }

  checkRefugeeNumberExists(id: string): Observable<boolean> {
    const url = `http://localhost:9090/ids/exists/${id}`;
    return this.http.get<boolean>(url);
  }

  login(credentials: any): Observable<any> {
    return this.http.post<any>(this.loginUrl, credentials);
  }

  getMentors(): Observable<any> {
    const url = 'http://localhost:8080/mentor';
    return this.http.get(url);
  }

  getDetailsByUsername(username: string, userType: string): Observable<any> {
    let endpoint = '';
    if (userType === 'Mentor') {
      endpoint = `${this.baseUrl}/mentor/by-username/${username}`;
    } else if (userType === 'Refugee') {
      endpoint = `${this.baseUrl}/refugee/by-username/${username}`;
    }
    return this.http.get(endpoint);
  }
}
