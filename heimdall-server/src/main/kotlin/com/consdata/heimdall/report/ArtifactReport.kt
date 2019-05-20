package com.consdata.heimdall.report

data class ArtifactReport(
        val name: ArtifactName,
        val version: ArtifactVersion,
        val date: GenerationDate,
        val git: GitCommit? = null,
        val modules: List<ArtifactModule>,
        val type: ArtifactType
)

data class GitCommit(val branch: GitBranch, val sha: String)

data class GitBranch(val name: String)

data class ArtifactModule(val name: ArtifactName? = null, val type: ArtifactType, val dependencies: List<ArtifactDependency>)

data class ArtifactDependency(val name: ArtifactName, val version: DependencyVersion, val dependencies: List<ArtifactDependency>)

data class DependencyVersion(val requested: ArtifactVersionRange, val resolved: ArtifactVersion)

enum class ArtifactType {
    Npm, Maven, Gradle
}

data class GenerationDate(val timestamp: Number)

data class ArtifactVersion(val major: Number, val minor: Number, val patch: Number) {
    fun versionString() = "$major.$minor.$patch"
}

data class ArtifactVersionRange(val range: String)

// do i need artifactGroup for npm artifactGroup and maven groupId?
data class ArtifactName(val artifact: String, val group: String? = null) {
    fun nameString(): String = if (group != null) "$group/$artifact" else artifact
}