import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {DependencyEntity, GridViewEntity, ProjectEntity, VersionEntity, VersionStatus} from './grid.models';

@Injectable()
export class GridService {

  constructor(private http: HttpClient) {
  }

  getLibsGridView(): Observable<GridViewEntity> {
    return this.http.get<GridViewEntity>('api/v1/matrix').pipe(
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
      return VersionStatus.VERY_OLD
    }
  }
}
