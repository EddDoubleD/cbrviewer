import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Convert } from 'src/app/model/convert';
import { environment } from 'src/environments/environments';

@Injectable({
  providedIn: 'root'
})
export class ExchangeService {

  constructor(private http: HttpClient) { }

  public getRates() : Observable<Array<Convert>> {
    return this.http.get<Array<Convert>>(`${environment.url}/api/v1/convert`);
  }
}
