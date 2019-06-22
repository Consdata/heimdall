export interface Dependency {
    name: string,
    version: string,
    resolution?: string,
    dependencies?: Dependency[];
}

export enum ModuleType {
    npm = 'Npm',
    maven = 'Maven',
    gradle = 'Gradle'
}

export interface Report {
    project: {
        name: string,
        version: string,
        type: ModuleType
    },
    git?: {
        branch: string,
        sha: string
    },
    timestamp: string,
    dependencies?: Dependency[];
}
