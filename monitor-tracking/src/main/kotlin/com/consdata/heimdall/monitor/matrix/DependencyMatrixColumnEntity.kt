package com.consdata.heimdall.monitor.matrix

import com.consdata.heimdall.monitor.ArtifactScope
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class DependencyMatrixColumnEntity(
        @Id var dependencyArtifact: String,
        var dependencyScope: ArtifactScope,
        var dependencyGroup: String?,
        var dependencyLatestMajor: Long,
        var dependencyLatestMinor: Long,
        var dependencyLatestPatch: Long
)
