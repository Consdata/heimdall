package com.consdata.heimdall.dependency.module

import javax.persistence.GeneratedValue
import javax.persistence.Id

data class ModuleDependencyEntity(
        @Id @GeneratedValue val id: Long? = null
)