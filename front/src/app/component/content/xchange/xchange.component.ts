import { Component, OnInit,  } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { Convert } from 'src/app/model/convert';
import { ExchangeService } from 'src/app/service/exchange/exchange.service';

@Component({
  selector: 'app-xchange',
  templateUrl: './xchange.component.html',
  styleUrls: ['./xchange.component.scss']
})
export class XchangeComponent implements OnInit {
  rate: Number = 1;
  
  currencies!: Observable<Array<Convert>>;
  
  form!: FormGroup;
  

  constructor(private fb: FormBuilder, 
    private exchangeService: ExchangeService) {}
  
  ngOnInit(): void {
    this.form = this.fb.group({
      fromAmount: 0,
      fromCurrency: null,
      toAmount: 0,
      toCurrency: null
    });

    this.currencies = this.exchangeService.getRates();
  }

  exchange() {
    const from = this.form.get("fromCurrency")?.value;
    const rateFrom = Number(from.value.replace(',', '.'))/from.nominal; 

    const to = this.form.get("toCurrency")?.value;
    const rateTo = Number(to.value.replace(',', '.'))/to.nominal;

    const rate = rateFrom/rateTo;

    var value = this.form.get("fromAmount")?.value;

    this.form.get("toAmount")?.setValue(value * rate);
  }

}
