package com.consdata.heimdall.monitor.matrix

import com.consdata.heimdall.monitor.overview.TrackingStatus
import javax.persistence.Id

data class DependencyMatrixVersionEntity(
        @Id var trackingId: Long,
        var projectId: Long,
        var dependencyId: Long,
        var versionMajor: Long,
        var versionMinor: Long,
        var versionPatch: Long,
        var status: TrackingStatus
)
