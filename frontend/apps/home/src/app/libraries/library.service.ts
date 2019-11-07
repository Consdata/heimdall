import {Injectable} from '@angular/core';
import {
  ArtifactVersion,
  MonitorTrackingOverviewEntry,
  MonitorTrackingService
} from '@heimdall-frontend/heimdall/monitor-tracking/api';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';


export enum VersionStatus {
  VERYOLD, OLD, OK, GOOD
}

export interface ProjectView {
  name: string;
  projectVersion: string;
  status: VersionStatus;
}

export interface LibView {
  name: string;
  currentVersion: string;
  libs: ProjectView[]
}

@Injectable({
  providedIn: 'root'
})
export class LibraryService {

  constructor(private monitorTrackingService: MonitorTrackingService) {
  }

  getLibsDashBoardView(): Observable<LibView[]> {
    return this.monitorTrackingService.overview().pipe(
      map(items => {
        const libs: { [key: string]: LibView } = {};
        for (let item of items) {
          const name = this.artifactName(item.dependency.group, item.dependency.name);
          if (!libs[name]) {
            libs[name] = {
              name: name,
              currentVersion: this.artifactVersion(item.latestDependency.version),
              libs: []
            }
          }
        }
        for (let item of items) {
          const name = this.artifactName(item.dependency.group, item.dependency.name);
          libs[name].libs.push({
            name: this.artifactName(item.project.group, item.project.name),
            projectVersion: this.artifactVersion(item.dependency.version),
            status: this.depednencyStatus(item)
          });
        }
        return Object.values(libs);
      })
    );
  }

  private depednencyStatus(item: MonitorTrackingOverviewEntry): VersionStatus {
    if (item.dependency.version.major === item.latestDependency.version.major && item.dependency.version.minor === item.latestDependency.version.minor) {
      return VersionStatus.GOOD;
    } else if (item.dependency.version.major === item.latestDependency.version.major) {
      return VersionStatus.OLD;
    } else {
      return VersionStatus.VERYOLD
    }
  }

  private artifactVersion(version: ArtifactVersion): string {
    return `${version.major}.${version.minor}.${version.patch}`;
  }

  private artifactName(group: string, name: string): string {
    return !!group ? `${group}/${name}` : `${name}`;
  }

}
