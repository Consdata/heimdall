"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var parser_1 = require("./parser");
var fs = require('file-system');
var lockfile = require('@yarnpkg/lockfile');
var _a = process.argv, args = _a.slice(2);
var pathToProject = args[0] || './';
var paths = {
    packageJson: pathToProject + "/package.json",
    yarnLock: pathToProject + "/yarn.lock"
};
// console.log(`Running Heimdall scanner in ${pathToProject}`);
var missingPaths = Object.keys(paths)
    .map(function (key) { return paths[key]; })
    .filter(function (path) { return !fs.existsSync(path); });
if (missingPaths.length > 0) {
    throw Error("Expected files not found [paths=" + JSON.stringify(missingPaths) + "]");
}
// const confFile = JSON.parse(fs.readFileSync('../../foxy-news/.heimdall.json'));
// - path mapping for modules (effectively white list to scan)
var packageFile = JSON.parse(fs.readFileSync(paths.packageJson));
var yarnLock = lockfile.parse(fs.readFileSync(paths.yarnLock, 'utf8')).object;
var report = parser_1.parse(packageFile, yarnLock);
// console.log(JSON.stringify(compressJson(report)));
console.log(JSON.stringify(report));
