import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurencyCodeComponent } from './curency-code.component';

describe('CurencyCodeComponent', () => {
  let component: CurencyCodeComponent;
  let fixture: ComponentFixture<CurencyCodeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CurencyCodeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CurencyCodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
