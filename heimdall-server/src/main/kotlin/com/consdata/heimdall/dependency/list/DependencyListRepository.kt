package com.consdata.heimdall.dependency.list

import org.springframework.data.jpa.repository.JpaRepository

interface DependencyListRepository : JpaRepository<DependencyEntity, Long> {

    fun existsByScopeAndGroupIdAndArtifactId(scope: DependencyScope, group: String, artifact: String): Boolean

}
