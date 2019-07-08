import {ArtifactVersion} from './artifact-version';

export interface Artifact {
  scope: string,
  group: string,
  name: string,
  version: ArtifactVersion
}
