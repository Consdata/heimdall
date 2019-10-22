package com.consdata.heimdall.monitor.matrix

import org.springframework.data.jpa.repository.JpaRepository


interface DependencyMatrixDependencyRepository : JpaRepository<DependencyMatrixDependencyEntity, String> {
    fun findByDependencyArtifactAndDependencyGroup(artifact: String, group: String?): DependencyMatrixDependencyEntity?
}
