export interface ProjectMetadata {
    name: string;
    version: string;
}

export class PomParser {

    getProjectMetadata(pomFile: string): ProjectMetadata {
        return {name: 'TODO name', version: 'TODO version'};
    }

}