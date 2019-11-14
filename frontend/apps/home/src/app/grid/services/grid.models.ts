export enum VersionStatus {
  VERY_OLD, OLD, OK, GOOD
}

export interface GridViewEntity {
  readonly projectEntities: ProjectEntity[];
  readonly dependencyEntities: DependencyEntity[];
  readonly versionEntities: VersionEntity[];
}

export interface ProjectEntity {
  readonly projectId: number,
  readonly projectArtifact: string,
  readonly projectGroup: string,
  readonly projectVersionMajor: number,
  readonly projectVersionMinor: number,
  readonly projectVersionPatch: number,
}

export interface DependencyEntity {
  readonly dependencyId: number,
  readonly dependencyArtifact: string,
  readonly dependencyGroup: string,
  readonly dependencyScope: string,
  readonly dependencyLatestMajor: number,
  readonly dependencyLatestMinor: number,
  readonly dependencyLatestPatch: number,
}

export interface VersionEntity {
  readonly projectId: number,
  readonly dependencyId: number,
  readonly versionMajor: number,
  readonly versionMinor: number,
  readonly versionPatch: number,
  status: VersionStatus,
}
