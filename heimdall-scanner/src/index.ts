import {NpmScanner} from './npm-scanner';
import {ModuleType} from './api';
import {MavenScanner} from './maven-scanner';

const [, , ...args] = process.argv;
const moduleType = args[0];
const pathToProject = args[1] || './';

switch (moduleType.toLowerCase()) {
    case ModuleType.npm: {
        console.log(new NpmScanner().scan(pathToProject));
        break;
    }
    case ModuleType.maven: {
        console.log(new MavenScanner().scan(pathToProject));
        break;
    }
    default: {
        throw Error(`Unsupported module type[moduleType=${moduleType}]`);
    }
}