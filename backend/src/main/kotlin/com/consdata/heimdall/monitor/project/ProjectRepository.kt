package com.consdata.heimdall.monitor.project

import com.consdata.heimdall.monitor.ArtifactScope
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<ProjectEntity, Long> {

    fun findByScopeAndGroupIdAndArtifact(scope: ArtifactScope, group: String?, artifact: String): ProjectEntity?

}