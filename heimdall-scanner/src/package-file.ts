export interface PackageFileDependencies {
    [key: string]: string;
}

export interface PackageFile {
    dependencies: PackageFileDependencies;
}