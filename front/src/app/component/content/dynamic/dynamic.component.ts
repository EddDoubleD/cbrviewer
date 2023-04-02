import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatSelect } from '@angular/material/select';
import { Observable } from 'rxjs';
import { Currency } from 'src/app/model/currency';
import { CurrencyService } from 'src/app/service/currency/currency.service';
import { DynamicRateService } from 'src/app/service/dynamic/dynamic-rate.service';

import * as Highcharts from 'highcharts';
import HC_stock from 'highcharts/modules/stock';

HC_stock(Highcharts);   


@Component({
  selector: 'app-dynamic',
  templateUrl: './dynamic.component.html',
  styleUrls: ['./dynamic.component.scss']
})
export class DynamicComponent implements OnInit {
  Highcharts = Highcharts;
  chartOptions = {};
  seriesOptions = [];


  uploading: boolean = false;
  data: any = [];
  currencies!: Observable<Array<Currency>>;
  
  @ViewChild('matSelect')
  matSelect!: MatSelect;
  
  range: FormGroup;

  constructor(
    private dynamicRateService: DynamicRateService,
    private currencyService: CurrencyService,
    private fb: FormBuilder) {
        this.range = this.fb.group({
          start: new FormControl<Date | null>(null),
          end: new FormControl<Date | null>(null)
        })
    }
  
  ngOnInit(): void {
    this.currencies = this.currencyService.getCurrency();
  }

  loadDynamic() {
    this.data = [];
    let values = []
    for(let value of this.matSelect.value) {
      values.push(value.id)
    }
    
    const start = this.range.get('start')?.value
    const end = this.range.get('end')?.value
    
    this.uploading = true;
    this.dynamicRateService.getDynamicRates(values.join(','), start, end).subscribe({
      next: response => {
        // Портируем настройки диаграммы
        this.chartOptions = {
          rangeSelector: {
            selected: 4
          },
          chart: {
            type: 'line',
          },
          title: {
            text: 'linechart',
          },
          yAxis: {
            plotLines: [{
              value: 0,
              width: 2,
              color: 'silver'
          }]},
          plotOptions: {
            series: {
              compare: 'percent',
              showInNavigator: true
            }  
          },

          tooltip: {
            pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.change}%)<br/>',
            valueDecimals: 2,
            split: true
          },

          series: response
        };

        setTimeout(() => {
          window.dispatchEvent(new Event('resize'));
          this.uploading = false;
        }, 
        300);
      }
    });
  }
}

