package com.consdata.heimdall.report.add

import com.consdata.heimdall.report.*
import java.util.regex.Pattern

internal class DefaultReportDtoParser : ReportDtoParser {

    private val versionPattern = Pattern.compile("^([0-9]+)(?:[.-]([0-9]+))?(?:[.-]([0-9]+))?(?:[.-]([0-9]+))?(?:[._-](.*))?\$").toRegex()

    override fun artifactType(type: AddReportModuleTypeDto) = when (type) {
        AddReportModuleTypeDto.Gradle -> ArtifactType.Gradle
        AddReportModuleTypeDto.Maven -> ArtifactType.Maven
        AddReportModuleTypeDto.Npm -> ArtifactType.Npm
    }

    override fun gitCommit(git: AddReportGitDto) = GitCommit(
            branch = GitBranch(git.branch),
            sha = git.sha
    )

    override fun reportDate(timestamp: String) = GenerationDate(timestamp.toLong())

    override fun artifactName(raw: String): ArtifactName {
        val parts = raw.split("/")
        return when (parts.size) {
            0 -> throw ParserException("Invalid artifactName name, missing parts")
            1 -> ArtifactName(artifact = parts[0])
            2 -> ArtifactName(group = parts[0], artifact = parts[1])
            else -> throw ParserException("Invalid artifactName name, to many parts")
        }
    }

    override fun artifactVersion(raw: String): ArtifactVersion {
        return versionPattern.matchEntire(raw)
                ?.destructured
                ?.let { (major, minor, patch, buildNumber, qualifier) ->
                    ArtifactVersion(
                            raw = raw,
                            major = major.toLongOrNull() ?: 0,
                            minor = minor.toLongOrNull() ?: 0,
                            patch = patch.toLongOrNull() ?: 0,
                            buildNumber = buildNumber.toLongOrNull() ?: 0,
                            qualifier = qualifier
                    )

                } ?: throw ParserException("Unknown artifact version, not matching pattern")
    }

    override fun dependencies(dependencies: Map<String, List<String>>) =
            dependencies.mapValues { entry ->
                entry.value
                        .map { it.split(":") }
                        .map { artifactDependency(it) }
            }

    private fun artifactDependency(it: List<String>): ArtifactDependency {
        return ArtifactDependency(
                scope = ArtifactType.Npm,
                group = it[0],
                name = it[1],
                artifactVersion = artifactVersion(it[2])
        )
    }

}