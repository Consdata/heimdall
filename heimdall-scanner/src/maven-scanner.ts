import {Scanner} from './scanner';
import {MavenDependencyTreeParser} from './maven-dependency-tree-parser';
import {Report} from './api';
import {exec, execSync} from 'child_process';

export class MavenScanner implements Scanner {

    scan(pathToProject: string): string {
        const dependencyTreeExecutionResult = execSync(`mvn clean package | mvn dependency:tree -f ${pathToProject} -DoutputType=dot -DappendOutput=true`)
            .toString('UTF-8')
        const parser = new MavenDependencyTreeParser(dependencyTreeExecutionResult);
        parser.parse(); // TODO zrobic tak zeby nie trzeba bylo tego wolac albo zeby parse od razu zwracalo report
        const report: Report = parser.getReport();

        return JSON.stringify(report, null, 2);
    }


}