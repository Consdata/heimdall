import {MonitorTrackingStatus} from './monitor-tracking-status';
import {Artifact} from './artifact';

export interface MonitorTrackingOverviewEntry {
  dependency: Artifact;
  latestDependency: Artifact;
  project: Artifact;
  status: MonitorTrackingStatus;
}
