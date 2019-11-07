package com.consdata.heimdall.monitor.matrix

import org.springframework.data.jpa.repository.JpaRepository


interface DependencyMatrixProjectRepository : JpaRepository<DependencyMatrixProjectEntity, Long> {
    fun findByProjectArtifactAndProjectGroup(artifact: String, group: String?): DependencyMatrixProjectEntity?
}
