package com.consdata.heimdall.monitor.matrix

import com.consdata.heimdall.monitor.overview.TrackingStatus
import javax.persistence.Id

data class DependencyMatrixCellEntity(
        @Id var trackingId: Long,
        var dependencyArtifact: String,
        var projectArtifact: String,
        var projectVersionMajor: Long,
        var projectVersionMinor: Long,
        var projectVersionPatch: Long,
        var versionMajor: Long,
        var versionMinor: Long,
        var versionPatch: Long,
        var status: TrackingStatus
)
