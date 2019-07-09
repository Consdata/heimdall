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
    libs: {
        [key: string]: string[]
    }
}
