import { async, TestBed } from '@angular/core/testing';
import { HeimdallMonitorTrackingApiModule } from './heimdall-monitor-tracking-api.module';

describe('HeimdallMonitorTrackingApiModule', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [HeimdallMonitorTrackingApiModule]
    }).compileComponents();
  }));

  it('should create', () => {
    expect(HeimdallMonitorTrackingApiModule).toBeDefined();
  });
});
