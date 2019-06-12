import { TestBed } from '@angular/core/testing';

import { LibDashBoardViewService } from './lib-dash-board-view.service';

describe('LibDashBoardViewService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LibDashBoardViewService = TestBed.get(LibDashBoardViewService);
    expect(service).toBeTruthy();
  });
});
