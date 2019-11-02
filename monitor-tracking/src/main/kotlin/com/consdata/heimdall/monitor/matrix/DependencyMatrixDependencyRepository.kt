package com.consdata.heimdall.monitor.matrix

import com.consdata.heimdall.monitor.ArtifactScope
import org.springframework.data.jpa.repository.JpaRepository


interface DependencyMatrixDependencyRepository : JpaRepository<DependencyMatrixDependencyEntity, String> {
    fun findByDependencyArtifactAndDependencyGroupAndDependencyScope(artifact: String, group: String?, scope: ArtifactScope): DependencyMatrixDependencyEntity?

}
