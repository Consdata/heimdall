export class MavenDependencyTreeParser {

    public getDependencyTree(dependencyTreeBashExecutionResult: string): {[key: string]: string[]} {
        const dependencyTree = dependencyTreeBashExecutionResult.match(/{(.|[\r\n])*}/g);
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
            return filtered;
        } else {
            throw Error('Could not parse dependency tree');
        }
    }

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