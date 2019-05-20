export function isObject(a: any) {
    return (!!a) && (a.constructor === Object);
}

export function isArray(a: any) {
    return (!!a) && (a.constructor === Array);
}