export enum ModuleType {
    npm = 'npm',
    maven = 'maven',
    gradle = 'gradle'
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
