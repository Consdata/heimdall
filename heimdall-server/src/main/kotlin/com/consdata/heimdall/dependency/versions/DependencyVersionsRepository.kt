package com.consdata.heimdall.dependency.versions

import org.springframework.data.jpa.repository.JpaRepository

interface DependencyVersionsRepository : JpaRepository<DependencyVersionEntity, Long> {

    fun existsByArtifactGroupAndArtifactNameAndMajorAndMinorAndPatch(group: String, name: String, major: Long, minor: Long, patch: Long): Boolean

}
