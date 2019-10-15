import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {of} from 'rxjs';
import {ArtifactVersion} from '@heimdall-frontend/heimdall/monitor-tracking/api';

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
  versionMajor: number,
  versionMinor: number,
  versionPatch: number,
}

export interface DependencyEntity {
  dependencyId: number,
  dependencyArtifact: string,
  dependencyGroup: string,
  dependencyScope: string,
  versionMajor: number,
  versionMinor: number,
  versionPatch: number,
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

  getLibsGridView(): GridViewEntity {
    const result: GridViewEntity = {
      projectEntities: this.mockProjectEntities(),
      dependencyEntities: this.mockDependencyEntities(),
      versionEntities: this.mockVersionEntities()
    };
    return result;
  }

  artifactVersion(artifact: ProjectEntity | DependencyEntity | VersionEntity): string {
    return `${artifact.versionMajor}.${artifact.versionMinor}.${artifact.versionPatch}`;
  }

  mockProjectEntities(): ProjectEntity[] {
    return [
      {
        projectId: 1,
        projectArtifact: 'ex-webforms',
        projectGroup: 'ex-webforms',
        versionMajor: 1,
        versionMinor: 2,
        versionPatch: 3,
      },
      {
        projectId: 2,
        projectArtifact: 'eximee-console-model-mapper',
        projectGroup: 'null',
        versionMajor: 4,
        versionMinor: 4,
        versionPatch: 1,
      }
    ];
  }

  mockDependencyEntities(): DependencyEntity[] {
    return [
      {
        dependencyId: 3,
        dependencyArtifact: 'typescript',
        dependencyGroup: '',
        dependencyScope: 'Npm',
        versionMajor: 3,
        versionMinor: 5,
        versionPatch: 3,
      },
      {
        dependencyId: 4,
        dependencyArtifact: 'tslint-config',
        dependencyGroup: '@consdata',
        dependencyScope: 'Npm',
        versionMajor: 1,
        versionMinor: 13,
        versionPatch: 0,
      }
    ];
  }

  mockVersionEntities(): VersionEntity[] {
    return [
      {
        projectId: 1,
        dependencyId: 3,
        versionMajor: 3,
        versionMinor: 4,
        versionPatch: 0,
        status: VersionStatus.GOOD,
      },
      {
        projectId: 1,
        dependencyId: 4,
        versionMajor: 1,
        versionMinor: 13,
        versionPatch: 0,
        status: VersionStatus.OK,
      },
      {
        projectId: 2,
        dependencyId: 4,
        versionMajor: 3,
        versionMinor: 5,
        versionPatch: 3,
        status: VersionStatus.OLD,
      }
    ];
  }
}
