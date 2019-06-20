package com.consdata.heimdall.monitor.overview

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class DependencyOverviewEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
        var projectId: Long,
        var trackingId: Long,
        var dependencyScope: DependencyScope,
        var dependencyGroup: String?,
        var dependencyArtifact: String,
        var dependencyLatestMajor: Long,
        var dependencyLatestMnior: Long,
        var dependencyLatestPath: Long,
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
