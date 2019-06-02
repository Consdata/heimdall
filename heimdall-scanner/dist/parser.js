"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var api_1 = require("./api");
function dependency(resolutions, name, version) {
    var nameWithVersion = name + "@" + version;
    var resolved = resolutions[nameWithVersion];
    return {
        name: name,
        version: version,
        resolution: resolved.version,
        dependencies: !resolved.dependencies ? undefined :
            Object.keys(resolved.dependencies).map(function (name) { return dependency(resolutions, name, resolved.dependencies[name]); })
    };
}
exports.dependency = dependency;
function parse(packageFile, yarnLock) {
    var dependencies = packageFile.dependencies;
    var timestamp = new Date().getTime();
    return {
        project: {
            name: packageFile.name,
            version: packageFile.version,
            type: api_1.ModuleType.npm
        },
        timestamp: "" + timestamp,
        modules: [
            {
                type: api_1.ModuleType.npm,
                name: packageFile.name,
                dependencies: Object.keys(dependencies)
                    .map(function (name) { return dependency(yarnLock, name, dependencies[name]); })
            }
        ]
    };
}
exports.parse = parse;
