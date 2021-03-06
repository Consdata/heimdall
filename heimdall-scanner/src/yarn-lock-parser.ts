import {ModuleType, Report} from './api';
import {PackageFile, PackageFileDependencies} from './package-file';
import {Parser} from './parser';

export class YarnLockParser implements Parser {

    private readonly report: Report;
    private parsed: boolean = false;

    constructor(private pkg: PackageFile, private lock: any) {
        this.report = {
            project: {
                name: this.pkg.name,
                version: this.pkg.version,
                type: ModuleType.npm
            },
            timestamp: `${new Date().getTime()}`,
            libs: {}
        };
    }

    getReport() {
        if (!this.parsed) {
            throw new Error('Not parsed');
        }
        return this.report;
    }

    parse(): void {
        if (this.parsed) {
            throw new Error('Already parsed');
        }

        const dependencies = [
            ...this.pkgDeps(this.pkg.dependencies),
            ...this.pkgDeps(this.pkg.devDependencies)
        ];

        let appName = `${this.parseName(this.pkg.name + '@')}:${this.pkg.version}`;
        this.dependencies(appName, dependencies);

        this.parsed = true;
    }

    private pkgDeps(deps: PackageFileDependencies): string[] {
        return Object
            .keys(deps)
            .map(name => `${name}@${deps[name]}`);
    }

    private dependencies(lib: string, dependencies: string[]) {
        if (this.report.libs[lib]) {
            // console.log(`Dependency already checked, skipping [name=${lib}]`);
            return;
        }

        const libDeps: string[] = this.report.libs[lib] = [];

        const resolved = dependencies.map(dep => ({
            name: this.parseName(dep),
            version: this.lock[dep].version,
            requested: dep,
            dependencies: Object.keys((this.lock[dep].dependencies || [])).map(name => `${name}@${this.lock[dep].dependencies[name]}`)
        }));

        resolved
            .map(tuple => `${tuple.name}:${this.trimVersion(tuple.version)}`)
            .filter(dependency => libDeps.indexOf(dependency) < 0)
            .forEach(dependency => libDeps.push(dependency));
        resolved
            .filter(tuple => tuple.dependencies.length > 0)
            .forEach(tuple => this.dependencies(`${tuple.name}:${this.trimVersion(tuple.version)}`, tuple.dependencies));
    }

    private parseName(dep: string): string {
        if (dep.indexOf('/') >= 0) {
            return dep.substr(0, dep.indexOf('@', 1)).replace('/', ':');
        } else {
            return ':' + dep.substr(0, dep.indexOf('@', 1));
        }
    }

    private trimVersion(raw: string): string {
        const [, second, third, fourth] = /^([0-9]*).([0-9]*).([0-9]*)(.*)$/.exec(raw) || [, 0, 0, 0];
        return `${second}.${third}.${fourth}`;
    }

}
