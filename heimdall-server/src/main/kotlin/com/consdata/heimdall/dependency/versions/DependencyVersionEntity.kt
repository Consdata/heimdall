package com.consdata.heimdall.dependency.versions

import javax.persistence.*

@Entity
@Table(
        indexes = [
            Index(columnList = "artifactGroup,artifactName"),
            Index(columnList = "artifactGroup,artifactName,major,minor,patch", unique = true)
        ]
)
data class DependencyVersionEntity(
        @Id @GeneratedValue val id: Long? = null,
        val scope: DependencyVersionScope,
        val timestamp: Long,
        val artifactGroup: String,
        val artifactName: String,
        val major: Long,
        val minor: Long,
        val patch: Long
)

