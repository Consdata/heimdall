package com.consdata.heimdall.monitor.tracking

import com.consdata.heimdall.monitor.ArtifactScope
import com.consdata.heimdall.monitor.project.ProjectEntity
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class TrackedDependencyEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
        val scope: ArtifactScope,
        val groupId: String?,
        val artifact: String,
        var latestMajor: Long,
        var latestMinor: Long,
        var latestPatch: Long
) {

    fun compare(major: Long, minor: Long, patch: Long) = when {
        latestMajor > major -> 1
        latestMajor < major -> -1
        latestMinor > minor -> 1
        latestMinor < minor -> -1
        latestPatch > patch -> 1
        latestPatch < patch -> -1
        else -> 0
    }

    fun setVersion(versionMajor: Long, versionMinor: Long, versionPatch: Long) {
        this.latestMajor = versionMajor
        this.latestMinor = versionMinor
        this.latestPatch = versionPatch
    }

}


