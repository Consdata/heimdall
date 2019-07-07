export interface MonitorTrackingOverviewEntryDto {
  id: number,
  projectId: number,
  trackingId: number,
  dependencyScope: "Npm" | "Maven" | "Gradle",
  dependencyGroup: string,
  dependencyArtifact: string,
  dependencyLatestMajor: number,
  dependencyLatestMinor: number,
  dependencyLatestPatch: number,
  projectGroup: string,
  projectArtifact: string,
  projectVersionMajor: number,
  projectVersionMinor: number,
  projectVersionPatch: number,
  versionMajor: number,
  versionMinor: number,
  versionPatch: number,
  status: "Current" | "Outdated"
}
