import {ModuleWithProviders, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {MonitorTrackingService} from '@heimdall-frontend/heimdall/monitor-tracking/api';
import {MonitorTrackingResetService} from './monitor-tracking-reset.service';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule
  ]
})
export class HeimdallMonitorTrackingRestModule {

  static forRoot(): ModuleWithProviders {
    return {
      ngModule: HeimdallMonitorTrackingRestModule,
      providers: [{provide: MonitorTrackingService, useClass: MonitorTrackingResetService}]
    };
  }

}
