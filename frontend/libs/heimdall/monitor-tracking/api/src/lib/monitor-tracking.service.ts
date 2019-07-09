import {Observable} from "rxjs";
import {MonitorTrackingOverview} from './monitor-tracking-overview';

export abstract class MonitorTrackingService {
  abstract overview(): Observable<MonitorTrackingOverview>;
}

