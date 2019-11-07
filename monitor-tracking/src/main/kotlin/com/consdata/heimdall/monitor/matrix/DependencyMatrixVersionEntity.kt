package com.consdata.heimdall.monitor.matrix

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class DependencyMatrixVersionEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val versionId: Long? = null,
        var projectId: Long,
        var dependencyId: Long,
        var versionMajor: Long,
        var versionMinor: Long,
        var versionPatch: Long
)
