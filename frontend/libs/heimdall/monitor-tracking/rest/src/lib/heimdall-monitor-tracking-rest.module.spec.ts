import { async, TestBed } from '@angular/core/testing';
import { HeimdallMonitorTrackingRestModule } from './heimdall-monitor-tracking-rest.module';

describe('HeimdallMonitorTrackingRestModule', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [HeimdallMonitorTrackingRestModule]
    }).compileComponents();
  }));

  it('should create', () => {
    expect(HeimdallMonitorTrackingRestModule).toBeDefined();
  });
});
