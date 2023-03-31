import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { Currency } from 'src/app/model/currency';
import { CurrencyService } from 'src/app/service/currency/currency.service';

@Component({
  selector: 'app-dynamic',
  templateUrl: './dynamic.component.html',
  styleUrls: ['./dynamic.component.scss']
})
export class DynamicComponent implements OnInit {
  uploading: boolean = false;
  data: any = [];
  currencies!: Observable<Array<Currency>>;
  
  range: FormGroup;

  constructor(
    private currencyService: CurrencyService,
    private fb: FormBuilder) {
        this.range = this.fb.group({
          start: new FormControl<Date | null>(null),
          end: new FormControl<Date | null>(null),      
        })
    }
  
  ngOnInit(): void {
    this.currencies = this.currencyService.getCurrency();
  }

  loadDynamic() {
      
  }
}
