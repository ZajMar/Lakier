import { TestBed } from '@angular/core/testing';

import { LocalitiesClientService } from './localities-client.service';

describe('LocalitiesClientService', () => {
  let service: LocalitiesClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LocalitiesClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
