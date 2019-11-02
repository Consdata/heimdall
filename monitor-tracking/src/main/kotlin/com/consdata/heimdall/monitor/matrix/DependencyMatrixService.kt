package com.consdata.heimdall.monitor.matrix

import org.springframework.stereotype.Service

@Service
internal class DependencyMatrixService(
        private val dependencyRepository: DependencyMatrixDependencyRepository,
        private val projectRepository: DependencyMatrixProjectRepository,
        private val versionRepository: DependencyMatrixVersionRepository) {

    fun getMatrix(): DependencyMatrixDto {
        val dependencies = dependencyRepository.findAll()
        val projects = projectRepository.findAll()
        val versions = versionRepository.findAll()
        return DependencyMatrixDto(dependencies, projects, versions)
    }
}
