package com.consdata.heimdall.monitor.matrix

import org.springframework.data.jpa.repository.JpaRepository


interface DependencyMatrixVersionRepository : JpaRepository<DependencyMatrixVersionEntity, Long> {
    fun findByProjectIdAndDependencyId(projectId: Long, dependencyId: Long): DependencyMatrixVersionEntity?
}
