package com.consdata.heimdall.monitor.matrix

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class DependencyMatrixProjectEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var projectId: Long? = null,
        var projectArtifact: String,
        var projectGroup: String?,
        var projectVersionMajor: Long,
        var projectVersionMinor: Long,
        var projectVersionPatch: Long
)
