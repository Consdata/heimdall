package com.consdata.heimdall.report

data class ArtifactReport(
        val name: ArtifactName,
        val version: ArtifactVersion,
        val date: GenerationDate,
        val git: GitCommit? = null,
        val dependencies: List<ArtifactDependency>,
        val type: ArtifactType
)

data class GitCommit(val branch: GitBranch, val sha: String)

data class GitBranch(val name: String)

data class ArtifactDependency(val name: ArtifactName, val version: DependencyVersion, val dependencies: List<ArtifactDependency>, val cyclicDep: Boolean)

data class DependencyVersion(val requested: ArtifactVersionRange, val resolved: ArtifactVersion)

enum class ArtifactType {
    Npm, Maven, Gradle
}

data class GenerationDate(val timestamp: Long)

data class ArtifactVersion(val major: Long, val minor: Long, val patch: Long) {
    fun versionString() = "$major.$minor.$patch"
}

data class ArtifactVersionRange(val range: String)

data class ArtifactName(val artifact: String, val group: String? = null) {
    fun nameString(): String = if (group != null) "$group/$artifact" else artifact
}
