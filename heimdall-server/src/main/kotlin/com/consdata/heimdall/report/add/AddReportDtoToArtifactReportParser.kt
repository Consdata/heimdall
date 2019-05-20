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

    override fun reportDate(timestamp: String) = GenerationDate(timestamp.toInt())

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
                    major = parts.getOrElse(0) { "0" }.toInt(),
                    minor = parts.getOrElse(1) { "0" }.toInt(),
                    patch = parts.getOrElse(2) { "0" }.toInt()
            )
            else -> throw ParserException("Invalid artifactName version, to many segments")
        }
    }

}

@Component
class AddReportDtoToArtifactReportParser {

    private val npmParser = NpmParser()

    internal fun of(dto: AddReportDto): ArtifactReport {
        val parser = parser(dto.type)
        val artifactName = parser.artifactName(dto.project.name)
        return ArtifactReport(
                name = artifactName,
                version = parser.artifactVersion(dto.project.version),
                date = parser.reportDate(dto.timestamp),
                git = if (dto.git != null) parser.gitCommit(dto.git!!) else null,
                modules = ofModules(artifactName, dto.modules ?: listOf()),
                type = parser.artifactType(dto.type)
        )
    }

    private fun ofModules(projectName: ArtifactName, modules: List<AddReportModuleDto>) = modules.map {
        val parser = parser(it.type)
        ArtifactModule(
                name = if (it.name != null) parser.artifactName(it.name!!) else projectName,
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
