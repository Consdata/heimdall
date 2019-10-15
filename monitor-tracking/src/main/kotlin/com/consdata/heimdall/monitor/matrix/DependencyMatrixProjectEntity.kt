package com.consdata.heimdall.monitor.matrix

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class DependencyMatrixProjectEntity(
        @Id var projectId: Long,
        var projectArtifact: String,
        var projectGroup: String?,
        var projectVersionMajor: Long,
        var projectVersionMinor: Long,
        var projectVersionPatch: Long
)
