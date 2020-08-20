package com.consdata.heimdall.monitor.project

import com.consdata.heimdall.monitor.ArtifactScope
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ProjectEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
        val scope: ArtifactScope,
        val groupId: String?,
        val artifact: String,
        var versionMajor: Long,
        var versionMinor: Long,
        var versionPatch: Long
) {

    fun compare(other: ProjectEntity) = when {
        versionMajor > other.versionMajor -> 1
        versionMajor < other.versionMajor -> -1
        versionMinor > other.versionMinor -> 1
        versionMinor < other.versionMinor -> -1
        versionPatch > other.versionPatch -> 1
        versionPatch < other.versionPatch -> -1
        else -> 0
    }

    fun setVersion(versionMajor: Long, versionMinor: Long, versionPatch: Long) {
        this.versionMajor = versionMajor
        this.versionMinor = versionMinor
        this.versionPatch = versionPatch
    }

}
