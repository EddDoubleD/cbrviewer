import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/model/user';
import { environment } from 'src/environments/environments';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  public login(credentials : User): Observable<any> {
    return this.http.post(`${environment.url}/api/auth/login`, {
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
  }

  public register(user : User): Observable<any> {
    return this.http.post(`${environment.url}/api/auth/register`, {
      username: user.username,
      email: user.email,
      password: user.password
    }, httpOptions);
  }
}
