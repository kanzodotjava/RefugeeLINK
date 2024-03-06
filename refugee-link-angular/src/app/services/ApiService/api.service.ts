import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  constructor(private http: HttpClient) {}

  sendFormData(data: any): Observable<any> {
    const endpointUrl = 'http://localhost:8080/refugee';
    return this.http.post(endpointUrl, data);
  }

  checkRefugeeNumberExists(id: string): Observable<boolean> {
    const url = `http://localhost:9090/ids/exists/${id}`;
    return this.http.get<boolean>(url);
  }
}
