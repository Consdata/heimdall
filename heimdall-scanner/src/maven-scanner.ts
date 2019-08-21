import {Scanner} from './scanner';
import {MavenDependencyTreeParser} from './maven-dependency-tree-parser';
import {ModuleType, Report} from './api';
import {execSync} from 'child_process';
import {PomParser} from './pom-parser';

const fs = require('file-system');

export class MavenScanner implements Scanner {

    scan(pathToProject: string): string {
        const dependencyTreeBashExecutionResult = execSync(`mvn clean package | mvn dependency:tree -f ${pathToProject} -DoutputType=dot -DappendOutput=true`)
            .toString('UTF-8')
        const pomFile = fs.readFileSync(`${pathToProject}/pom.xml`, 'utf8').toString();
        const projectMetadata = new PomParser().getProjectMetadata(pomFile);

        const dependencyTree = new MavenDependencyTreeParser().getDependencyTree(dependencyTreeBashExecutionResult);
        const report: Report = this.getReport(projectMetadata.name, projectMetadata.version, dependencyTree);

        return JSON.stringify(report, null, 2);
    }

    private getReport(name: string, version: string, libs: {[key: string]: string[]}): Report {
        return {
            project: {
                name: name,
                version: version,
                type: ModuleType.maven
            },
            timestamp: `${new Date().getTime()}`,
            libs: libs
        };
    }


}