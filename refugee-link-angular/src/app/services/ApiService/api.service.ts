import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  constructor(private http: HttpClient) {}

  sendFormData(data: any): Observable<any> {
    const endpointUrl = 'http://localhost:8080/refugee/register';
    return this.http.post(endpointUrl, data);
  }
}
