package com.consdata.heimdall.dependency.list

import javax.persistence.*

@Entity
@Table(
        indexes = [
            Index(columnList = "artifactGroup,artifactName"),
            Index(columnList = "artifactGroup,artifactName,major,minor,patch", unique = true)
        ]
)
data class DependencyListEntity(
        @Id @GeneratedValue val id: Long? = null,
        val timestamp: Long,
        val artifactGroup: String,
        val artifactName: String,
        val major: Long,
        val minor: Long,
        val patch: Long
)

