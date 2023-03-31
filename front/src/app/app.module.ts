import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { RegisterComponent } from './component/register/register/register.component';

import { authInterceptorProviders } from './service/helpers/auth.interceptor';
import { LoginComponent } from './component/login/login/login.component';
import { HomeComponent } from './component/content/home/home.component';
import { XchangeComponent } from './component/content/xchange/xchange.component';
import { DynamicComponent } from './component/content/dynamic/dynamic.component';
import { CurencyCodeComponent } from './component/content/curency-code/curency-code.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DynamicChartComponent } from './component/wiget/dynamic-chart/dynamic-chart.component';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatPaginatorModule } from '@angular/material/paginator' ;
import { MatTableModule } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { MatMomentDateModule } from '@angular/material-moment-adapter'

import { HighchartsChartModule } from 'highcharts-angular';


@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    HomeComponent,
    XchangeComponent,
    DynamicComponent,
    CurencyCodeComponent,
    DynamicChartComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,  
    HttpClientModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatPaginatorModule,
    MatTableModule,
    MatSortModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    HighchartsChartModule,
    MatDatepickerModule,
    MatMomentDateModule,
    MatCardModule,
    MatProgressSpinnerModule 
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
