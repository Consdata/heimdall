export interface PackageFileDependencies {
    [key: string]: string;
}

export interface PackageFile {
    name: string;
    version: string;
    dependencies: PackageFileDependencies;
}
