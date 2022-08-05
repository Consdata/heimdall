import {ProjectDependency} from './ProjectDependency';

export type Dependency = {
  name: string,
  newestVersion: string,
  projectVersions: ProjectDependency
}
