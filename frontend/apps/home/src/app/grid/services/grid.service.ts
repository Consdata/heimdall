import {Injectable} from '@angular/core';

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
        projectGroup: '',
        versionMajor: 4,
        versionMinor: 4,
        versionPatch: 1,
      },
      {
        projectId: 3,
        projectArtifact: 'example 3',
        projectGroup: '',
        versionMajor: 5,
        versionMinor: 4,
        versionPatch: 1,
      },
      {
        projectId: 4,
        projectArtifact: 'example 4',
        projectGroup: 'example',
        versionMajor: 4,
        versionMinor: 2,
        versionPatch: 1,
      },
      {
        projectId: 5,
        projectArtifact: 'example 5',
        projectGroup: '',
        versionMajor: 1,
        versionMinor: 4,
        versionPatch: 1,
      }
    ];
  }

  mockDependencyEntities(): DependencyEntity[] {
    return [
      {
        dependencyId: 1,
        dependencyArtifact: 'typescript',
        dependencyGroup: '',
        dependencyScope: 'Npm',
        versionMajor: 3,
        versionMinor: 5,
        versionPatch: 3,
      },
      {
        dependencyId: 2,
        dependencyArtifact: 'tslint-config',
        dependencyGroup: '@consdata',
        dependencyScope: 'Npm',
        versionMajor: 1,
        versionMinor: 13,
        versionPatch: 0,
      },
      {
        dependencyId: 3,
        dependencyArtifact: 'example 3',
        dependencyGroup: '@consdata',
        dependencyScope: 'Npm',
        versionMajor: 1,
        versionMinor: 13,
        versionPatch: 0,
      },
      {
        dependencyId: 4,
        dependencyArtifact: 'example 4',
        dependencyGroup: '@consdata',
        dependencyScope: 'Npm',
        versionMajor: 1,
        versionMinor: 13,
        versionPatch: 0,
      },
      {
        dependencyId: 5,
        dependencyArtifact: 'example 5',
        dependencyGroup: '@consdata',
        dependencyScope: 'Npm',
        versionMajor: 1,
        versionMinor: 13,
        versionPatch: 0,
      },
      {
        dependencyId: 6,
        dependencyArtifact: 'example 6',
        dependencyGroup: '@consdata',
        dependencyScope: 'Npm',
        versionMajor: 1,
        versionMinor: 13,
        versionPatch: 0,
      },
      {
        dependencyId: 7,
        dependencyArtifact: 'example 7',
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
        dependencyId: 1,
        versionMajor: 3,
        versionMinor: 5,
        versionPatch: 3,
        status: VersionStatus.OLD,
      },
      {
        projectId: 2,
        dependencyId: 2,
        versionMajor: 3,
        versionMinor: 3,
        versionPatch: 2,
        status: VersionStatus.OK,
      },
      {
        projectId: 2,
        dependencyId: 4,
        versionMajor: 10,
        versionMinor: 556,
        versionPatch: 389,
        status: VersionStatus.OK,
      },
      {
        projectId: 3,
        dependencyId: 4,
        versionMajor: 1,
        versionMinor: 1,
        versionPatch: 3,
        status: VersionStatus.OK,
      },
      {
        projectId: 4,
        dependencyId: 4,
        versionMajor: 3,
        versionMinor: 4,
        versionPatch: 3,
        status: VersionStatus.OLD,
      },
      {
        projectId: 4,
        dependencyId: 5,
        versionMajor: 7,
        versionMinor: 5,
        versionPatch: 3,
        status: VersionStatus.GOOD,
      },
      {
        projectId: 4,
        dependencyId: 6,
        versionMajor: 3,
        versionMinor: 2,
        versionPatch: 1,
        status: VersionStatus.GOOD,
      },
      {
        projectId: 4,
        dependencyId: 7,
        versionMajor: 3,
        versionMinor: 1,
        versionPatch: 3,
        status: VersionStatus.OLD,
      },
      {
        projectId: 5,
        dependencyId: 1,
        versionMajor: 1,
        versionMinor: 2,
        versionPatch: 6,
        status: VersionStatus.OK,
      },
      {
        projectId: 5,
        dependencyId: 3,
        versionMajor: 6,
        versionMinor: 5,
        versionPatch: 3,
        status: VersionStatus.OK,
      }
    ];
  }
}
