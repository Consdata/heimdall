import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";

export enum VersionStatus {
  VERYOLD, OLD, OK, GOOD
}

export interface GridViewEntity {
  projectEntities: ProjectEntity[];
  dependencyEntities: DependencyEntity[];
  versionEntities: VersionEntity[];
}

export interface ProjectEntity {
  projectId: number,
  projectArtifact: string,
  projectGroup: string,
  projectVersionMajor: number,
  projectVersionMinor: number,
  projectVersionPatch: number,
}

export interface DependencyEntity {
  dependencyId: number,
  dependencyArtifact: string,
  dependencyGroup: string,
  dependencyScope: string,
  dependencyLatestMajor: number,
  dependencyLatestMinor: number,
  dependencyLatestPatch: number,
}

export interface VersionEntity {
  projectId: number,
  dependencyId: number,
  versionMajor: number,
  versionMinor: number,
  versionPatch: number,
  status: VersionStatus,
}

@Injectable({
  providedIn: 'root'
})
export class GridService {

  constructor(private http: HttpClient) {
  }

  getLibsGridView(): Observable<GridViewEntity> {
    return this.http.get<GridViewEntity>('api/monitor-tracking/v1/matrix').pipe(
      map(data => {
          for (let version of data.versionEntities) {
            let dependency = data.dependencyEntities.find(dependency => dependency.dependencyId === version.dependencyId);
            if (dependency) {
              version.status = this.getVersionStatus(version, dependency);
            }
          }
          return data;
        }
      )); // TODO ICL-1295 analogicznie jak libraries
  }

  projectVersion(projectEntity: ProjectEntity): string {
    return `${projectEntity.projectVersionMajor}.${projectEntity.projectVersionMinor}.${projectEntity.projectVersionPatch}`;
  }

  dependencyVersion(dependencyEntity: DependencyEntity): string {
    return `${dependencyEntity.dependencyLatestMajor}.${dependencyEntity.dependencyLatestMinor}.${dependencyEntity.dependencyLatestPatch}`;
  }

  getVersionStatus(version: VersionEntity, dependency: DependencyEntity): VersionStatus {
    if (version.versionMajor === dependency.dependencyLatestMajor && version.versionMinor === dependency.dependencyLatestMinor) {
      return VersionStatus.GOOD;
    } else if (version.versionMajor === dependency.dependencyLatestMajor) {
      return VersionStatus.OLD;
    } else {
      return VersionStatus.VERYOLD
    }
  }
}
