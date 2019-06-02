export interface ModuleDependency {
    name: string,
    version: string,
    resolution?: string,
    dependencies?: ModuleDependency[];
}

export enum ModuleType {
    npm = 'Npm',
    maven = 'Maven',
    gradle = 'Gradle'
}

export interface ReportModule {
    name: string; // empty for single module projects
    path?: string; //  empty for single module projects
    type: ModuleType;
    dependencies?: ModuleDependency[];
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
    modules?: ReportModule[];
}
