package com.consdata.heimdall.monitor.artifact

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

//@Entity
data class ArtifactEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null
)
