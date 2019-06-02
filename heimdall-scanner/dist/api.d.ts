export interface ModuleDependency {
    name: string;
    version: string;
    resolution?: string;
    dependencies?: ModuleDependency[];
}
export declare enum ModuleType {
    npm = "Npm",
    maven = "Maven",
    gradle = "Gradle"
}
export interface ReportModule {
    name: string;
    path?: string;
    type: ModuleType;
    dependencies?: ModuleDependency[];
}
export interface Report {
    project: {
        name: string;
        version: string;
        type: ModuleType;
    };
    git?: {
        branch: string;
        sha: string;
    };
    timestamp: string;
    modules?: ReportModule[];
}
