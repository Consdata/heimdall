package com.consdata.heimdall.monitor.overview

import com.consdata.heimdall.monitor.overview.matrix.DependencyOverviewMatrix
import com.consdata.heimdall.monitor.overview.matrix.MatrixMapper
import org.springframework.stereotype.Service

@Service
internal class DependencyOverviewService(private val repository: DependencyOverviewRepository, private val matrixMapper: MatrixMapper) {

    fun getOverview(): List<DependencyOverviewEntity> = repository.findAll()

    fun getOverviewMatrix(): DependencyOverviewMatrix = matrixMapper.map(repository.findAll())

}
