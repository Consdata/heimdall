package com.consdata.heimdall.report

import java.lang.IllegalStateException

data class ArtifactDependency(
        val scope: ArtifactType,
        val group: String,
        val name: String,
        private val major: Long? = null,
        private val minor: Long? = null,
        private val patch: Long? = null,
        val artifactVersion: ArtifactVersion?
) {
    fun version() = artifactVersion ?: ArtifactVersion(
            major ?: throw IllegalStateException("Neither ArtifactVersion, neither major version provided"),
            minor ?: 0,
            patch ?: 0
    )
}

data class ArtifactReport(
        val name: ArtifactName,
        val version: ArtifactVersion,
        val date: GenerationDate,
        val git: GitCommit? = null,
        val dependencies: Map<String, List<ArtifactDependency>>,
        val type: ArtifactType) {
    fun rootDependencies() = dependencies["${name.nameString()}:${version.versionString()}"] ?: listOf()
}

data class GitCommit(val branch: GitBranch, val sha: String)

data class GitBranch(val name: String)

enum class ArtifactType {
    Npm, Maven
}

data class GenerationDate(val timestamp: Long)

data class ArtifactVersion(
        val major: Long,
        val minor: Long = 0,
        val patch: Long = 0,
        val buildNumber: Long = 0,
        val qualifier: String = "",
        val raw: String = "$major.$minor.$patch") {
    fun versionString() = raw
}

data class ArtifactName(val artifact: String, val group: String? = null) {
    fun nameString(): String = "${group ?: ""}:$artifact"
}
