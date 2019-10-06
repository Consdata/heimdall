package com.consdata.heimdall.monitor.overview.matrix

import com.consdata.heimdall.monitor.overview.TrackingStatus

data class DependencyOverviewMatrixCell(
        var projectId: Long,
        var trackingId: Long,
        var projectVersionMajor: Long,
        var projectVersionMinor: Long,
        var projectVersionPatch: Long,
        var versionMajor: Long,
        var versionMinor: Long,
        var versionPatch: Long,
        var status: TrackingStatus
)
