package com.consdata.heimdall.monitor.overview

import org.springframework.data.jpa.repository.JpaRepository

internal interface DependencyOverviewRepository : JpaRepository<DependencyOverviewEntity, Long>
