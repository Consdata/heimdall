import {Report} from './api';
import {PackageFile} from './package-file';
import {parse} from './parser';

const fs = require('file-system');
const lockfile = require('@yarnpkg/lockfile');

const [, , ...args] = process.argv;
const pathToProject = args[0] || './';
const paths: { [key: string]: string } = {
    packageJson: `${pathToProject}/package.json`,
    yarnLock: `${pathToProject}/yarn.lock`
};

console.log(`Running Heimdall scanner in ${pathToProject}`);

const missingPaths = Object.keys(paths)
    .map(key => paths[key])
    .filter(path => !fs.existsSync(path));
if (missingPaths.length > 0) {
    throw Error(`Expected files not found [paths=${JSON.stringify(missingPaths)}]`);
}

// const confFile = JSON.parse(fs.readFileSync('../../foxy-news/.heimdall.json'));
// - path mapping for modules (effectively white list to scan)
const packageFile = JSON.parse(fs.readFileSync(paths.packageJson)) as PackageFile;
const yarnLock = lockfile.parse(fs.readFileSync(paths.yarnLock, 'utf8')).object;
const rootDependencies = packageFile.dependencies;

const report: Report = parse(rootDependencies, yarnLock);

// console.log(JSON.stringify(compressJson(report)));
console.log(JSON.stringify(report));
