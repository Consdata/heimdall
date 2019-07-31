import {Scanner} from './scanner';
import {PackageFile} from './package-file';
import {YarnLockParser} from './yarn-lock-parser';
import {Report} from './api';

const fs = require('file-system');
const lockfile = require('@yarnpkg/lockfile');

export class NpmScanner implements Scanner {

    scan(pathToProject: string): string {
        const paths: { [key: string]: string } = {
            packageJson: `${pathToProject}/package.json`,
            yarnLock: `${pathToProject}/yarn.lock`
        };

        const missingPaths = Object.keys(paths)
            .map(key => paths[key])
            .filter(path => !fs.existsSync(path));
        if (missingPaths.length > 0) {
            throw Error(`Expected files not found [paths=${JSON.stringify(missingPaths)}]`);
        }

        const packageFile = JSON.parse(fs.readFileSync(paths.packageJson)) as PackageFile;
        const yarnLock = lockfile.parse(fs.readFileSync(paths.yarnLock, 'utf8')).object;
        const parser: YarnLockParser = new YarnLockParser(packageFile, yarnLock);

        parser.parse();
        const report: Report = parser.getReport();

        return JSON.stringify(report, null, 2);
    }

}