package com.consdata.heimdall.monitor.overview

data class Version(
        var major: Long,
        var minor: Long,
        var patch: Long
)

data class Dependency(
        var scope: DependencyScope,
        var group: String?,
        var artifact: String,
        var latest: Version
)

data class Project(
        var group: String?,
        var artifact: String,
        var version: Version
)

data class DependencyOverview(
        val dependency: Dependency,
        val project: Project,
        val usedVersion: Version,
        val status: TrackingStatus
)
