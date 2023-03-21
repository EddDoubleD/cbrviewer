import { LiveAnnouncer } from '@angular/cdk/a11y';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Observable, of } from 'rxjs';
import { Currency } from 'src/app/model/currency';
import { CurrencyService } from 'src/app/service/currency/currency.service';
import { StringUtils } from 'src/app/service/utils/stringUtils';

@Component({
  selector: 'app-curency-code',
  templateUrl: './curency-code.component.html',
  styleUrls: ['./curency-code.component.scss']
})
export class CurencyCodeComponent implements OnInit {
  displayedColumns = ['id', 'name', 'engName', 'nominal', 'parentCode'];
  dataSource: MatTableDataSource<Currency>;
  loading: boolean = true;

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;

  currency?: Observable<any>;

  constructor(private currencyService: CurrencyService,
    private liveAnnouncer: LiveAnnouncer) {
    const currency: Currency[] = [];
    this.dataSource = new MatTableDataSource(currency);
  }
  ngOnInit(): void {
    this.currencyService.getCurrency().subscribe({
      next: response => {
        this.dataSource = new MatTableDataSource(response);
        this.loading = false;
        this.dsInit();
      }, error: err => console.log(err)
    })
  }

  announceSortChange(sortState: Sort) {
    // This example uses English messages. If your application supports
    // multiple language, you would internationalize these strings.
    // Furthermore, you can customize the message to add additional
    // details about the values being sorted.
    if (sortState.direction) {
      this.liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this.liveAnnouncer.announce('Sorting cleared');
    }
  }
  
  /**
   * Set the paginator and sort after the view init since this component will
   * be able to query its view for the initialized paginator and sort.
   */
  dsInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  /**
   * Applies a filter by processing the entered value
   * 
   * @param event keyup-event
   */
  applyFilter(event: Event) {
    of((event.target as HTMLTextAreaElement).value)
      .pipe(StringUtils.trim(), StringUtils.toLowerCase())
      .subscribe((i) => {
        this.dataSource.filter = i;
      }).unsubscribe();
  }
}
