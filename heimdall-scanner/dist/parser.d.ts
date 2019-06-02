import { ModuleDependency, Report } from './api';
import { PackageFile } from './package-file';
export declare function dependency(resolutions: any, name: string, version: string): ModuleDependency;
export declare function parse(packageFile: PackageFile, yarnLock: any): Report;
