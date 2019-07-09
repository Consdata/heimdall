package com.consdata.heimdall.report.add

import com.consdata.heimdall.report.*
import org.springframework.stereotype.Component

class ParserException(message: String) : RuntimeException(message)

private interface Parser {
    fun artifactName(raw: String): ArtifactName
    fun artifactVersion(raw: String): ArtifactVersion
    fun reportDate(timestamp: String): GenerationDate
    fun gitCommit(git: AddReportGitDto): GitCommit
    fun artifactType(type: AddReportModuleTypeDto): ArtifactType
    fun dependencies(dependencies: Map<String, List<String>>): Map<String, List<ArtifactDependency>>
}

private class NpmParser : Parser {

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
        val parts = raw.split(".")
        return when (parts.size) {
            0 -> throw ParserException("Invalid artifactName version, missing segments")
            in 1..3 -> ArtifactVersion(
                    major = parts.getOrElse(0) { "0" }.toLong(),
                    minor = parts.getOrElse(1) { "0" }.toLong(),
                    patch = parts.getOrElse(2) { "0" }.toLong()
            )
            else -> throw ParserException("Invalid artifactName version, to many segments")
        }
    }

    override fun dependencies(dependencies: Map<String, List<String>>) =
            dependencies.mapValues { entry ->
                entry.value
                        .map { it.split(":") }
                        .map { artifactDependency(it) }
            }

    private fun artifactDependency(it: List<String>): ArtifactDependency {
        val version = it[2].split(".")
        return ArtifactDependency(
                scope = ArtifactType.Npm,
                group = it[0],
                name = it[1],
                major = version[0].toLong(),
                minor = version[1].toLong(),
                patch = version[2].toLong()
        )
    }

}

@Component
class AddReportDtoToArtifactReportParser {

    private val npmParser = NpmParser()

    internal fun of(dto: AddReportDto): ArtifactReport {
        val parser = parser(dto.project.type)
        return ArtifactReport(
                name = parser.artifactName(dto.project.name),
                version = parser.artifactVersion(dto.project.version),
                date = parser.reportDate(dto.timestamp),
                git = if (dto.git != null) parser.gitCommit(dto.git!!) else null,
                dependencies = parser.dependencies(dto.libs),
                type = parser.artifactType(dto.project.type)
        )
    }

    private fun parser(type: AddReportModuleTypeDto) = when (type) {
        AddReportModuleTypeDto.Npm -> npmParser
        AddReportModuleTypeDto.Gradle -> throw ParserException("Not supported artifactName type")
        AddReportModuleTypeDto.Maven -> throw ParserException("Not supported artifactName type")
    }

}
