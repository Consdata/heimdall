package com.consdata.heimdall.report.list

import org.springframework.data.jpa.repository.JpaRepository

interface DependencyRepository : JpaRepository<DependencyEntity, Long> {

    fun existsByScopeAndGroupIdAndArtifactId(scope: String, groupId: String, artifactId: String): Boolean

}
