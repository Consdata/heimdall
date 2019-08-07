package com.consdata.heimdall.report

data class ArtifactDependency(
        val scope: ArtifactType,
        val group: String,
        val name: String,
        val version: ArtifactVersion
)

data class ArtifactReport(
        val name: ArtifactName,
        val version: ArtifactVersion,
        val date: GenerationDate,
        val git: GitCommit? = null,
        val dependencies: Map<String, List<ArtifactDependency>>,
        val type: ArtifactType) {
    fun rootDependencies() = dependencies["${name.nameString()}:${version.raw}"] ?: listOf()
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
        val raw: String) {

    fun versionString(): String {
        val version = listOfNotNull(major, minor, patch, buildNumber, qualifier).joinToString(separator = ".")
        return if (qualifier.isNotBlank()) {
            "$version=$qualifier"
        } else {
            version
        }
    }

}

data class ArtifactName(val artifact: String, val group: String? = null) {
    fun nameString(): String = "${group ?: ""}:$artifact"
}

