package com.consdata.heimdall.report

import java.util.*

class ArtifactReportTestSupport {

    companion object {

        fun anArtifactDependency(group: String, artifact: String, major: Number, minor: Number, patch: Number) = ArtifactDependency(
                name = ArtifactName(
                        artifact = artifact,
                        group = group
                ),
                version = DependencyVersion(
                        resolved = ArtifactVersion(major, minor, patch),
                        requested = ArtifactVersionRange("^$major.0.0")
                ),
                dependencies = listOf()
        )


        fun anArtifactReport(dependencies: List<ArtifactDependency>) = ArtifactReport(
                name = ArtifactName("test-artifactName"),
                version = ArtifactVersion(1, 0, 0),
                type = ArtifactType.Npm,
                date = GenerationDate(Date().time),
                modules = listOf(
                        ArtifactModule(
                                type = ArtifactType.Npm,
                                dependencies = dependencies
                        )
                )
        )

    }

}