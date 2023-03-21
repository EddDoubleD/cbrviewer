import { ComponentFixture, TestBed } from '@angular/core/testing';

import { XchangeComponent } from './xchange.component';

describe('XchangeComponent', () => {
  let component: XchangeComponent;
  let fixture: ComponentFixture<XchangeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ XchangeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(XchangeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
