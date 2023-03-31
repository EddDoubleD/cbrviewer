import { Component, Input, OnInit } from '@angular/core';
import * as Highcharts from 'highcharts';
import HC_exporting from 'highcharts/modules/exporting';
import HC_stock from 'highcharts/modules/stock';

HC_stock(Highcharts); 

@Component({
  selector: 'app-dynamic-chart',
  templateUrl: './dynamic-chart.component.html',
  styleUrls: ['./dynamic-chart.component.scss']
})
export class DynamicChartComponent implements OnInit {
  Highcharts = Highcharts;
  chartOptions = {};
  seriesOptions = [{
    "name": "val",
    "data": [
      [1673298000000, 19.0855],
      [1673384400000, 18.9406],
      [1673470800000, 18.7803],
      [1673557200000, 18.3556],
      [1673643600000, 18.3043],
      [1673902800000, 18.4979],
      [1673989200000, 18.6414],
      [1674075600000, 18.6560],
      [1674162000000, 18.7072],
      [1674248400000, 18.5926],
      [1674507600000, 18.5827],
      [1674594000000, 18.6163],
      [1674680400000, 18.6814],
      [1674766800000, 18.7173],
      [1674853200000, 18.7744],
      [1675112400000, 18.8510],
      [1675198800000, 19.0963]
    ]
  }];

  

  @Input() data = [];

  ngOnInit(): void {
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
        }]
      },
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

    series: this.seriesOptions
    };

    HC_exporting(Highcharts);

    setTimeout(() => {
      window.dispatchEvent(
        new Event('resize')
      );
    }, 300);
  }
}
