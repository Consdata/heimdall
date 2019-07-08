import {Dependency, ModuleType, Report} from './api';
import {PackageFile} from './package-file';

export function trimVersion(raw: string): string {
    const [, second, third, fourth] = /^([0-9]*).([0-9]*).([0-9]*)(.*)$/.exec(raw) || [, 0, 0, 0];
    return `${second}.${third}.${fourth}`;
}

export function dep(depth: number): string {
    let dep = '';
    for (let idx = 0; idx < depth; ++idx) {
        dep += ' ';
    }
    return `${dep} - `;
}

export function dependency(resolutions: any, name: string, version: string, seen: string[]): Dependency {
    const nameWithVersion = `${name}@${version}`;
    const resolved = resolutions[nameWithVersion];
    const nameWithResolvedVersion = `${name}@${resolved.version}`;
    let includeDeps = true;
    if (seen.indexOf(nameWithResolvedVersion) >= 0) {
        // console.warn(`Dependency already seen [dependency=${nameWithResolvedVersion}, seen=${[...seen, nameWithResolvedVersion].join(' -> ')}]`);
        includeDeps = false;
    }
    return {
        name: name,
        version: version,
        resolution: trimVersion(resolved.version),
        cyclicDep: !includeDeps,
        dependencies: !includeDeps || !resolved.dependencies ? undefined :
            Object.keys(resolved.dependencies).map(
                name => dependency(
                    resolutions,
                    name,
                    resolved.dependencies[name],
                    [...seen, nameWithResolvedVersion])
            )
    }
}

export function parse(packageFile: PackageFile, yarnLock: any): Report {
    const dependencies = {
        ...packageFile.devDependencies,
        ...packageFile.dependencies
    };
    const timestamp = new Date().getTime();
    return {
        project: {
            name: packageFile.name,
            version: packageFile.version,
            type: ModuleType.npm
        },
        timestamp: `${timestamp}`,
        dependencies: Object.keys(dependencies)
            .map(name => dependency(yarnLock, name, dependencies[name], [packageFile.name]))
    };
}
