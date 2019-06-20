package com.consdata.heimdall.monitor.tracking

import com.consdata.heimdall.monitor.ArtifactScope
import org.springframework.data.jpa.repository.JpaRepository

interface TrackedDependencyRepository : JpaRepository<TrackedDependencyEntity, Long> {

    fun existsByScopeAndGroupAndArtifact(scope: ArtifactScope, groupId: String?, artifact: String): Boolean

}
