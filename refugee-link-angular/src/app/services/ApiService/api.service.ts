  import { Injectable } from '@angular/core';
  import { HttpClient, HttpParams } from '@angular/common/http';
  import { Observable } from 'rxjs';

  @Injectable({
    providedIn: 'root',
  })
  export class ApiService {
    private baseUrl = 'http://localhost:8080';
    private loginUrl = 'https://localhost:7165/login';
    private baseUrlEntity = 'https://localhost:7165';

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

    getMentors(): Observable<any[]> {
      const url = `${this.baseUrl}/mentor`;
      return this.http.get<any[]>(url);
    }

    acceptMentor(mentorId: number): Observable<any> {
      const url = `${this.baseUrl}/mentor/${mentorId}/certify`;
      return this.http.put(url, null);
    }

    rejectMentor(mentorId: number): Observable<any> {
      const url = `${this.baseUrl}/mentor/${mentorId}/reject`;
      return this.http.put(url, null);
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

    applyToMentorship(username: string, mentorId: number): Observable<any> {
      const url = `${this.baseUrl}/refugee/${username}/mentor`;
      const queryParams = new HttpParams().set('mentorId', mentorId.toString());
      return this.http.put(url, null, {
        params: queryParams,
        responseType: 'text',
      });
    }

    getMentorByUsername(username: string): Observable<any> {
      const url = `http://localhost:8080/refugee/by-username/${username}/mentor`;
      return this.http.get<any>(url);
    }

    getRefugeesByMentorUsername(username: string): Observable<any[]> {
      const url = `http://localhost:8080/mentor/${username}/refugees`;
      return this.http.get<any[]>(url);
    }

    getMentorUsernameByUsername(username: string): Observable<any> {
      const url = `http://localhost:8080/refugee/by-username/${username}/mentor/username`;
      return this.http.get<any>(url);
    }

    sendFormDataOrganization(data: any): Observable<any> {
      const endpointUrl = `${this.baseUrlEntity}/organization`;
      return this.http.post(endpointUrl, data);
    }

    getOrganizations(): Observable<any[]> {
      const endpointUrl = `${this.baseUrlEntity}/organization`;
      return this.http.get<any[]>(endpointUrl);
    }

    deleteOrganization(id: number): Observable<any> {
      const endpointUrl = `${this.baseUrlEntity}/organization/${id}`;
      return this.http.delete(endpointUrl);
    }

    updateOrganization(organizationData: any): Observable<any> {
      const endpointUrl = `${this.baseUrlEntity}/organization/${organizationData.id}`;
      return this.http.put(endpointUrl, organizationData);
    }
  }
