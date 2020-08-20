package com.consdata.heimdall.monitor.overview

import com.consdata.heimdall.monitor.ArtifactScope
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class DependencyOverviewEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
        var projectId: Long,
        var trackingId: Long,
        var dependencyScope: ArtifactScope,
        var dependencyGroup: String?,
        var dependencyArtifact: String,
        var dependencyLatestMajor: Long,
        var dependencyLatestMinor: Long,
        var dependencyLatestPatch: Long,
        var projectGroup: String?,
        var projectArtifact: String,
        var projectVersionMajor: Long,
        var projectVersionMinor: Long,
        var projectVersionPatch: Long,
        var versionMajor: Long,
        var versionMinor: Long,
        var versionPatch: Long,
        var status: TrackingStatus
)
