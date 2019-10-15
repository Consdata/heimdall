package com.consdata.heimdall.monitor.matrix

import com.consdata.heimdall.monitor.ArtifactScope
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class DependencyMatrixDependencyEntity(
        @Id var dependencyId: Long,
        var dependencyArtifact: String,
        var dependencyGroup: String?,
        var dependencyScope: ArtifactScope,
        var dependencyLatestMajor: Long,
        var dependencyLatestMinor: Long,
        var dependencyLatestPatch: Long
)
