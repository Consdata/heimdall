import {Parser} from './parser';
import {ModuleType, Report} from './api';

export class MavenDependencyTreeParser implements Parser {

    constructor(private dependencyTreeExecutionResult: string) {
    }

    getReport(): Report {
        return {
            project: {
                name: 'project name TODO',
                version: 'project version TODO',
                type: ModuleType.maven
            },
            timestamp: `${new Date().getTime()}`,
            libs: {}
        };
    }

    // TODO ta metoda moze od razu zwracac report? albo przeniesc to do getReport i usunac parse() z interfejsu
    private parseDependencyTree(dependencyTreeExecutionResult: string): {[key: string]: string[]} {
        const dependencyTree = this.dependencyTreeExecutionResult.match(/{(.|[\r\n])*}/g);
        if (dependencyTree) {
            const filtered =
                dependencyTree[0]
                    .split('\n')
                    .filter(line => line.indexOf('->') !== -1)
                    .map(line => this.extractDependencyInfo(line))
                    .map(line => line.split(' -> '))
                    .map(linePair => [this.extractNameAndVersion(linePair[0]), this.extractNameAndVersion(linePair[1])])
                    .reduce(
                        (grouped: {[key: string]: string[]}, linePair: string[]) => ({
                                ...grouped,
                                [linePair[0]]: grouped[linePair[0]] ? [...grouped[linePair[0]], linePair[1]] : [linePair[1]]
                        }),
                        {}
                    );

            console.log(JSON.stringify(filtered, null, 2));
            return filtered;
        } else {
            throw Error('Could not parse dependency tree');
        }
    }

    parse(): void {

    } // TODO usunac

    private extractNameAndVersion(raw: string) {
        const split = raw.split(':');
        return `${split[0]}:${split[1]}:${split[3]}`;
    }

    /**
     * Returns dependency info in format: project:packaging:version(:scope) ->  project:packaging:version:scope
     * @param line
     */
    private extractDependencyInfo(line: string): string {
        return line.substring(line.indexOf('\"') + 1, line.lastIndexOf('\"')).replace(/\"/g, '');
    }

}