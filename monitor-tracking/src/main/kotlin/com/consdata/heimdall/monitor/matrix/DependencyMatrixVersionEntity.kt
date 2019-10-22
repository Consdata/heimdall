package com.consdata.heimdall.monitor.matrix

import com.consdata.heimdall.monitor.overview.TrackingStatus
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

data class DependencyMatrixVersionEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val versionId: Long? = null,
        var projectId: Long,
        var dependencyId: Long,
        var versionMajor: Long,
        var versionMinor: Long,
        var versionPatch: Long,
        var status: TrackingStatus
)
