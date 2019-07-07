export interface Artifact {
  scope: string,
  group: string,
  name: string,
  version: {
    major: number,
    minor: number,
    patch: number
  }
}
