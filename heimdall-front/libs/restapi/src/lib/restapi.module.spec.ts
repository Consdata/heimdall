import { async, TestBed } from '@angular/core/testing';
import { RestapiModule } from './restapi.module';

describe('RestapiModule', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [RestapiModule]
    }).compileComponents();
  }));

  it('should create', () => {
    expect(RestapiModule).toBeDefined();
  });
});
