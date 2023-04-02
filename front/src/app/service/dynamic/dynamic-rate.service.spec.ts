import { TestBed } from '@angular/core/testing';

import { DynamicRateService } from './dynamic-rate.service';

describe('DynamicRateService', () => {
  let service: DynamicRateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DynamicRateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
