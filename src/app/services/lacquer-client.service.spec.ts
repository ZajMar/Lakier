import { TestBed } from '@angular/core/testing';

import { LacquerClientService } from './lacquer-client.service';

describe('LacquerClientService', () => {
  let service: LacquerClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LacquerClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
