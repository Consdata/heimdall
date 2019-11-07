package com.consdata.heimdall.monitor.overview

import org.springframework.stereotype.Service

@Service
internal class DependencyOverviewService(private val repository: DependencyOverviewRepository) {

    fun getOverview(): List<DependencyOverviewEntity> = repository.findAll()

}
