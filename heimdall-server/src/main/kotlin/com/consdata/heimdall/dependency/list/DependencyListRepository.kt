package com.consdata.heimdall.dependency.list

import org.springframework.data.jpa.repository.JpaRepository

interface DependencyListRepository : JpaRepository<DependencyListEntity, Long> {

    fun existsByArtifactGroupAndArtifactNameAndMajorAndMinorAndPatch(group: String, name: String, major: Long, minor: Long, patch: Long): Boolean

}