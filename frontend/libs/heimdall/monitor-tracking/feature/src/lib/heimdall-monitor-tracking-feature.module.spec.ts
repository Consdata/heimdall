import { async, TestBed } from '@angular/core/testing';
import { HeimdallMonitorTrackingFeatureModule } from './heimdall-monitor-tracking-feature.module';

describe('HeimdallMonitorTrackingFeatureModule', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [HeimdallMonitorTrackingFeatureModule]
    }).compileComponents();
  }));

  it('should create', () => {
    expect(HeimdallMonitorTrackingFeatureModule).toBeDefined();
  });
});
