import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Currency } from 'src/app/model/currency';
import { environment } from 'src/environments/environments';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {

  constructor(private http: HttpClient) { }

  getCurrency() : Observable<Array<Currency>> {
    return this.http.get<Array<Currency>>(`${environment.url}/api/v1/currency`);
  }
}
