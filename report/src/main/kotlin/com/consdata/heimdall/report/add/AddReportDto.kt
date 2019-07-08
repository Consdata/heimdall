package com.consdata.heimdall.report.add

/**
 * Interfejs modelu zdarzeń/commandów (domenowych).
 * W przypadku potrzeb zmian pod REST należy sukcesywnie wprowadzać DTO.
 * Zmiany w modelu wprowadzamy tylko przyrostowo.
 */

internal enum class AddReportModuleTypeDto {
    Npm, Maven, Gradle
}

internal data class AddReportProjectDto(
        var name: String,
        var version: String,
        var type: AddReportModuleTypeDto
)

internal data class AddReportGitDto(
        var branch: String,
        var sha: String
)

internal data class AddReportDto(
        var project: AddReportProjectDto,
        var git: AddReportGitDto?,
        var timestamp: String,
        var libs: Map<String, List<String>>
)
