package com.consdata.heimdall.monitor.matrix

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class DependencyMatrixRowEntity(
        @Id var projectArtifact: String,
        var projectGroup: String?,
        var projectId: Long
)
