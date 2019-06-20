package com.consdata.heimdall.monitor.tracking

import com.consdata.heimdall.monitor.ArtifactScope
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

//@Entity
data class TrackedDependencyEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
        val scope: ArtifactScope,
        val groupId: String?,
        val artifact: String
)


