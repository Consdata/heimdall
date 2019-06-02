package com.consdata.heimdall.dependency.list

import javax.persistence.*

@Entity
@Table(
        indexes = [
            Index(columnList = "scope"),
            Index(columnList = "scope,groupId,artifactId", unique = true)
        ]
)
data class DependencyEntity(
        @Id @GeneratedValue val id: Long? = null,
        val scope: DependencyScope,
        val groupId: String,
        val artifactId: String
)
