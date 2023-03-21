import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/model/user';

const AUTH_API = 'http://localhost:4200/api/auth';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  public login(credentials : User): Observable<any> {
    return this.http.post(`${AUTH_API}/login`, {
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
  }

  public register(user : User): Observable<any> {
    return this.http.post(`${AUTH_API}/register`, {
      username: user.username,
      email: user.email,
      password: user.password
    }, httpOptions);
  }
}
