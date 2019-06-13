package com.consdata.heimdall.reports.add

import com.consdata.heimdall.reports.*
import org.springframework.stereotype.Component

class ParserException(message: String) : RuntimeException(message)

private interface Parser {
    fun artifactName(raw: String): ArtifactName
    fun artifactVersion(raw: String): ArtifactVersion
    fun reportDate(timestamp: String): GenerationDate
    fun gitCommit(git: AddReportGitDto): GitCommit
    fun artifactType(type: AddReportModuleTypeDto): ArtifactType
    fun dependencyVersion(requested: String, resolved: String): DependencyVersion
}

private class NpmParser : Parser {

    override fun dependencyVersion(requested: String, resolved: String) = DependencyVersion(
            requested = ArtifactVersionRange(requested),
            resolved = artifactVersion(resolved)
    )

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
                modules = ofModules(dto.modules ?: listOf()),
                type = parser.artifactType(dto.project.type)
        )
    }

    private fun ofModules(modules: List<AddReportModuleDto>) = modules.map {
        val parser = parser(it.type)
        ArtifactModule(
                name = parser.artifactName(it.name),
                type = parser.artifactType(it.type),
                dependencies = ofDependencies(parser, it.dependencies ?: listOf())
        )
    }


    private fun ofDependencies(parser: Parser, dependencies: List<AddReportModuleDependencyDto>): List<ArtifactDependency> = dependencies.map {
        ArtifactDependency(
                name = parser.artifactName(it.name),
                version = parser.dependencyVersion(it.version, it.resolution ?: it.version),
                dependencies = ofDependencies(parser, it.dependencies ?: listOf()))

    }


    private fun parser(type: AddReportModuleTypeDto) = when (type) {
        AddReportModuleTypeDto.Npm -> npmParser
        AddReportModuleTypeDto.Gradle -> throw ParserException("Not supported artifactName type")
        AddReportModuleTypeDto.Maven -> throw ParserException("Not supported artifactName type")
    }

}
