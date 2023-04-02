import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environments';

@Injectable({
  providedIn: 'root'
})
export class DynamicRateService {

  constructor(private http: HttpClient) {}

  /**
   * Получение динамик нескольких курсов валют, в один период времени
   * @param ids перечень идентификаторов валют,  
   * @param from дата начала периода
   * @param to дата окончания периода
   * @returns массив 
   */
  public getDynamicRates(ids: string, from: Date, to : Date) : Observable<any> {
    return this.http.get((`${environment.url}/api/v1/dynamic?from=${this.datePrepare(from)}&to=${this.datePrepare(to)}&id=${ids}`))
  }

  private datePrepare(date: any) : string {    
    return date.format('DD/MM/yyyy')
  }
}
