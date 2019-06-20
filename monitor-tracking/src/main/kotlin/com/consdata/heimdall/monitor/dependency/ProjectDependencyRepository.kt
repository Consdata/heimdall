package com.consdata.heimdall.monitor.dependency

import org.springframework.data.jpa.repository.JpaRepository

interface ProjectDependencyRepository : JpaRepository<ProjectDependencyEntity, Long>
