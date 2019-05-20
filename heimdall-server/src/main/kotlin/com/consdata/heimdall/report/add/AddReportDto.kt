package com.consdata.heimdall.report.add

/**
 * Interfejs modelu zdarzeń/commandów (domenowych).
 * W przypadku potrzeb zmian pod REST należy sukcesywnie wprowadzać DTO.
 * Zmiany w modelu wprowadzamy tylko przyrostowo.
 */

internal enum class AddReportModuleTypeDto {
    Npm, Maven, Gradle
}

internal data class AddReportModuleDependencyDto(
        var name: String,
        var version: String,
        var resolution: String?,
        var dependencies: List<AddReportModuleDependencyDto>?
)

internal data class AddReportModuleDto(
        var name: String?,
        var path: String?,
        var type: AddReportModuleTypeDto,
        var dependencies: List<AddReportModuleDependencyDto>?
)

internal data class AddReportProjectDto(
        var name: String,
        var version: String
)

internal data class AddReportGitDto(
        var branch: String,
        var sha: String
)

internal data class AddReportDto(
        var type: AddReportModuleTypeDto,
        var timestamp: String,
        var project: AddReportProjectDto,
        var git: AddReportGitDto?,
        var modules: List<AddReportModuleDto>?
)
