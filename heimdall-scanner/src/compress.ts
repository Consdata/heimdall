import {isArray, isObject} from './util';

export function compressJson(obj: any): any {
    if (isObject(obj)) {
        return Object.keys(obj)
            .reduce(
                (result, key) => ({
                    ...result,
                    [key.substring(0, 1)]: compressJson(obj[key])
                }),
                {}
            );
    } else if (isArray(obj)) {
        return (obj as any[]).map(e => compressJson(e));
    } else {
        return obj;
    }
}