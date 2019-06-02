package com.consdata.heimdall.dependency.versions

import com.consdata.heimdall.report.ArtifactType

enum class DependencyVersionScope {

    Npm, Maven, Gradle;

    companion object {
        fun ofReportType(type: ArtifactType) = when (type) {
            ArtifactType.Npm -> Npm
            ArtifactType.Maven -> Maven
            ArtifactType.Gradle -> Gradle
            else -> throw Error()
        }
    }

}
