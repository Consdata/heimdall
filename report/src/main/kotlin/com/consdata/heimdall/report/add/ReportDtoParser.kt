package com.consdata.heimdall.report.add

import com.consdata.heimdall.report.*

internal interface ReportDtoParser {
    fun artifactName(raw: String): ArtifactName
    fun artifactVersion(raw: String): ArtifactVersion
    fun reportDate(timestamp: String): GenerationDate
    fun gitCommit(git: AddReportGitDto): GitCommit
    fun artifactType(type: AddReportModuleTypeDto): ArtifactType
    fun dependencies(dependencies: Map<String, List<String>>): Map<String, List<ArtifactDependency>>
}