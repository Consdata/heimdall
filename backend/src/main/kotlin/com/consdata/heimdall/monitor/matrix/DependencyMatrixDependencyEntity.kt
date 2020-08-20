package com.consdata.heimdall.monitor.matrix

import com.consdata.heimdall.monitor.ArtifactScope
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class DependencyMatrixDependencyEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val dependencyId: Long? = null,
        var dependencyArtifact: String,
        var dependencyGroup: String?,
        var dependencyScope: ArtifactScope,
        var dependencyLatestMajor: Long,
        var dependencyLatestMinor: Long,
        var dependencyLatestPatch: Long
)
