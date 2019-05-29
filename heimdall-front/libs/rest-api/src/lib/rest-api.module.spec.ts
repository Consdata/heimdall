import { async, TestBed } from '@angular/core/testing';
import { RestApiModule } from './rest-api.module';

describe('RestApiModule', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [RestApiModule]
    }).compileComponents();
  }));

  it('should create', () => {
    expect(RestApiModule).toBeDefined();
  });
});
