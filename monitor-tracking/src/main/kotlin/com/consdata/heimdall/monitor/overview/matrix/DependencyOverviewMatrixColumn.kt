package com.consdata.heimdall.monitor.overview.matrix

import com.consdata.heimdall.monitor.ArtifactScope

data class DependencyOverviewMatrixColumn(
        var dependencyScope: ArtifactScope,
        var dependencyGroup: String?,
        var dependencyArtifact: String,
        var dependencyLatestMajor: Long,
        var dependencyLatestMinor: Long,
        var dependencyLatestPatch: Long
)
