import {Dependency, ModuleType, Report} from './api';
import {PackageFile} from './package-file';

export function dependency(resolutions: any, name: string, version: string): Dependency {
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

export function parse(packageFile: PackageFile, yarnLock: any): Report {
    const dependencies = packageFile.dependencies;
    const timestamp = new Date().getTime();
    return {
        project: {
            name: packageFile.name,
            version: packageFile.version,
            type: ModuleType.npm
        },
        timestamp: `${timestamp}`,
        dependencies: Object.keys(dependencies)
            .map(name => dependency(yarnLock, name, dependencies[name]))
    };
}
