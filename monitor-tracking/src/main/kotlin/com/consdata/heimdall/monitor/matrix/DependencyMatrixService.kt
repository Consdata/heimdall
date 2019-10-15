package com.consdata.heimdall.monitor.matrix

import org.springframework.stereotype.Service

@Service
internal class DependencyMatrixService(
        private val dependencyRepository: DependencyMatrixDependencyRepository,
        private val projectRepository: DependencyMatrixProjectRepository,
        private val versionRepository: DependencyMatrixVersionRepository) {

    fun getMatrix(): DependencyMatrixDto {
        val columns = dependencyRepository.findAll()
        val rows = projectRepository.findAll()
        val cells = versionRepository.findAll()
        return DependencyMatrixDto(columns, rows, cells)
    }
}
