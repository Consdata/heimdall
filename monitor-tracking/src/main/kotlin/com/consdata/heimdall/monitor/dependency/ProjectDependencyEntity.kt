package com.consdata.heimdall.monitor.dependency

import com.consdata.heimdall.monitor.artifact.ArtifactEntity
import com.consdata.heimdall.monitor.project.ProjectEntity
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ProjectDependencyEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
        val project: ProjectEntity,
        val dependency: ArtifactEntity
)
