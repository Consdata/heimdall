package com.consdata.heimdall.report.add

import com.consdata.heimdall.report.*
import org.springframework.stereotype.Component

class ParserException(message: String) : RuntimeException(message)

@Component
class AddReportDtoToArtifactReportParser {

    private val defaultParser = DefaultReportDtoParser()

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
        AddReportModuleTypeDto.Npm -> defaultParser
        AddReportModuleTypeDto.Gradle -> throw ParserException("Not supported artifactName type")
        AddReportModuleTypeDto.Maven -> defaultParser
    }

}
