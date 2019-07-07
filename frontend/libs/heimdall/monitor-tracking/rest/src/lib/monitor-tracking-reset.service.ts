import {Injectable} from '@angular/core';
import {MonitorTrackingOverview, MonitorTrackingService} from '@heimdall-frontend/heimdall/monitor-tracking/api';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {map, tap} from 'rxjs/operators';
import {MonitorTrackingOverviewEntryDto} from './monitor-tracking-overview-entry-dto';

@Injectable()
export class MonitorTrackingResetService implements MonitorTrackingService {

  constructor(private http: HttpClient) {
  }

  overview(): Observable<MonitorTrackingOverview> {
    return this.http.get<MonitorTrackingOverviewEntryDto[]>('api/monitor-tracking/v1/overview').pipe(
      tap(result => console.log(result)),
      map(_ => [])
    )
  }

}
