import {ModuleDependency, ModuleType, Report} from './api';
import {PackageFileDependencies} from './package-file';

export function dependency(resolutions: any, name: string, version: string): ModuleDependency {
    const nameWithVersion = `${name}@${version}`;
    const resolved = resolutions[nameWithVersion];
    return {
        name: name,
        version: version,
        resolution: resolved.version,
        dependencies: !resolved.dependencies ? undefined :
            Object.keys(resolved.dependencies).map(name => dependency(resolutions, name, resolved.dependencies[name]))
    }
}

export function parse(dependencies: PackageFileDependencies, yarnLock: any): Report {
    const timestamp = new Date().getTime();
    return {
        project: {
            name: 'Project name',
            version: '1.0.0-SNAPSHOT'
        },
        timestamp: `${timestamp}`,
        git: {
            branch: 'master',
            sha: '256aaa'
        },
        modules: [
            {
                type: ModuleType.npm,
                dependencies: Object.keys(dependencies)
                    .map(name => dependency(yarnLock, name, dependencies[name]))
            }
        ]
    };
}
