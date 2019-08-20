export interface ProjectMetadata {
    name: string;
    version: string;
}

export interface PomFile {
    artifactId: string;
    groupId: string;
    version: string;
    parent?: ParentData;
}

export interface ParentData {
    groupId: string;
    version: string;
}

const XML = require('pixl-xml');

export class PomParser {

    getProjectMetadata(pomFile: string): ProjectMetadata {
        return {name: this.getProjectName(pomFile), version: this.getProjectVersion(pomFile)};
    }

    getProjectName(pomFile: string): string {
        const pom: PomFile = XML.parse(pomFile);
        const artifactId: string = pom.artifactId;
        if (pom.parent !== undefined) {
            return pom.parent.groupId + ':' + artifactId;
        } else {
            return pom.groupId + ':' + artifactId;
        }
    }

    getProjectVersion(pomFile: string): string {
        const pom: PomFile = XML.parse(pomFile);
        if (pom.parent !== undefined) {
            return pom.parent.version;
        } else {
            return pom.version;
        }
    }
}