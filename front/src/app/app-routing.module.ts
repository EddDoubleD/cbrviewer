import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CurencyCodeComponent } from './component/content/curency-code/curency-code.component';
import { DynamicComponent } from './component/content/dynamic/dynamic.component';
import { HomeComponent } from './component/content/home/home.component';
import { XchangeComponent } from './component/content/xchange/xchange.component';
import { LoginComponent } from './component/login/login/login.component';
import { RegisterComponent } from './component/register/register/register.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'xchange', component: XchangeComponent },
  { path: 'dynamic', component: DynamicComponent },
  { path: 'curencyCode', component: CurencyCodeComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
