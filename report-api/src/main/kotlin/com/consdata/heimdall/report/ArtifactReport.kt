package com.consdata.heimdall.report

data class ArtifactDependency(
        val group: String,
        val name: String,
        val major: Long,
        val minor: Long,
        val patch: Long,
        val scope: ArtifactType
)

data class ArtifactReport(
        val name: ArtifactName,
        val version: ArtifactVersion,
        val date: GenerationDate,
        val git: GitCommit? = null,
        val dependencies: Map<String, List<ArtifactDependency>>,
        val type: ArtifactType
) {

    fun rootDependencies() = dependencies["${name.nameString()}:${version.versionString()}"] ?: listOf()

}

data class GitCommit(val branch: GitBranch, val sha: String)

data class GitBranch(val name: String)

enum class ArtifactType {
    Npm, Maven, Gradle
}

data class GenerationDate(val timestamp: Long)

data class ArtifactVersion(val major: Long, val minor: Long, val patch: Long) {
    fun versionString() = "$major.$minor.$patch"
}

data class ArtifactName(val artifact: String, val group: String? = null) {
    fun nameString(): String = "${group ?: ""}:$artifact"
}
