package com.consdata.heimdall.dependnecies

import org.springframework.data.jpa.repository.JpaRepository

interface DependencyRepository : JpaRepository<DependencyEntity, Long> {

    fun existsByScopeAndGroupIdAndArtifactId(scope: String, groupId: String, artifactId: String): Boolean

}
