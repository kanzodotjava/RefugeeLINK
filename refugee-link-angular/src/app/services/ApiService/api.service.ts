import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, map } from 'rxjs';

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

  getAllMentors(): Observable<any[]> {
    const url = `${this.baseUrl}/mentor`;
    return this.http.get<any[]>(url);
  }

  getMentors(): Observable<any[]> {
    const url = `${this.baseUrl}/mentor/certified`;
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

  organizationLogin(credentials: {
    username: string;
    password: string;
  }): Observable<any> {
    const endpointUrl = `${this.baseUrlEntity}/organization/login`;
    return this.http.post(endpointUrl, credentials);
  }

  getOrganizationIdByUsername(username: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrlEntity}/username/${username}`);
  }

  createFormation(orgId: number, formationData: any): Observable<any> {
    return this.http.post(
      `${this.baseUrlEntity}/organization/createFormation/${orgId}`,
      formationData
    );
  }

  getFormationsByStatus(status: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/formation/status/${status}`);
  }

  getOrganizationNameById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrlEntity}/name/${id}`, { responseType: 'text' });
  }
  

  applyToFormation(refugeeId: number, formationId: number): Observable<string> {
    const url = `${this.baseUrl}/formation/refugees/${refugeeId}/formations/${formationId}/register`;
    return this.http.post<string>(url, null, {
      responseType: 'text' as 'json',
    });
  }
  getCurrentFormationsByUsername(username: string): Observable<any[]> {
    const url = `${this.baseUrl}/refugee/current-formation/${username}`;
    return this.http.get<any[]>(url);
  }

  getFormationDetails(id: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/formation/${id}`);
  }

  getCurrentFormationByUsername(username: string): Observable<any> {
    return this.http.get<any>(
      `${this.baseUrl}/refugee/current-formation/${username}`
    );
  }

  getFormationsByUsername(username: string): Observable<any[]> {
    return this.http.get<any[]>(`http://localhost:8080/formation/formations-by-organization-username/${username}`);
  }

  getRefugeesByFormation(formationId: string): Observable<any[]> {
    return this.http.get<any[]>(`http://localhost:8080/RefugeeFormation/refugees-by-formation/${formationId}`);
  }

  getStatusByUsername(username: string): Observable<string> {
    return this.http.get(`http://localhost:8080/mentor/status/${username}`, { responseType: 'text' });
  }
  approveRefugee(refugeeId: number, formationId: number): Observable<any> {
    return this.http.put(`http://localhost:8080/RefugeeFormation/toggleApproval/${refugeeId}/${formationId}`, {}); 
  }

  getRefugeeApprovalStatus(refugeeId: number, formationId: number): Observable<any> {
    return this.http.get(`http://localhost:8080/RefugeeFormation/isApproved/${refugeeId}/${formationId}`);
  }

  expelRefugeeFromFormation(refugeeId: number, formationId: number): Observable<any> {
    return this.http.delete(`http://localhost:8080/RefugeeFormation/expel/${refugeeId}/${formationId}`);
  }

  getRefugeeIdByUsername(username: string): Observable<number> {
    return this.http.get(`http://localhost:8080/refugee/id/${username}`, { responseType: 'text' })
      .pipe(
        map((response: string) => parseInt(response, 10)) // Parse response string to number
      );
  }

}
