import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {
  MonitorTrackingOverview,
  MonitorTrackingOverviewEntry,
  MonitorTrackingService,
  MonitorTrackingStatus
} from '@heimdall-frontend/heimdall/monitor-tracking/api';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {MonitorTrackingOverviewEntryDto} from './monitor-tracking-overview-entry-dto';

@Injectable()
export class MonitorTrackingResetService implements MonitorTrackingService {

  readonly excluded: string[] = [
    'eximee-console',
    'eximee-console-form-starter',
    'eximee-console-metadata-editor',
    'eximee-console-repository-browser',
    'eximee-console-model-mapper'];

  constructor(private http: HttpClient) {
  }

  overview(): Observable<MonitorTrackingOverview> {
    return this.http.get<MonitorTrackingOverviewEntryDto[]>('api/monitor-tracking/v1/overview').pipe(
      map((dtos: MonitorTrackingOverviewEntryDto[]) => {
        return dtos.filter(dto => {return !this.excluded.includes(dto.projectArtifact);});
      }),
      map(result => result.map(item => this.mapItem(item)))
    )
  }

  private mapItem(item: MonitorTrackingOverviewEntryDto): MonitorTrackingOverviewEntry {
    return {
      project: {
        scope: item.dependencyScope,
        group: item.projectGroup,
        name: item.projectArtifact,
        version: {
          major: item.projectVersionMajor,
          minor: item.projectVersionMinor,
          patch: item.projectVersionPatch
        }
      },
      dependency: {
        scope: item.dependencyScope,
        group: item.dependencyGroup,
        name: item.dependencyArtifact,
        version: {
          major: item.versionMajor,
          minor: item.versionMinor,
          patch: item.versionPatch
        }
      },
      latestDependency: {
        scope: item.dependencyScope,
        group: item.dependencyGroup,
        name: item.dependencyArtifact,
        version: {
          major: item.dependencyLatestMajor,
          minor: item.dependencyLatestMinor,
          patch: item.dependencyLatestPatch
        }
      },
      status: MonitorTrackingStatus[item.status]
    };
  }

}
