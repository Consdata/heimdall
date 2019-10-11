package com.consdata.heimdall.monitor.matrix

import org.springframework.stereotype.Service

@Service
internal class DependencyMatrixService(
        private val columnRepository: DependencyMatrixColumnRepository,
        private val rowRepository: DependencyMatrixRowRepository,
        private val cellRepository: DependencyMatrixCellRepository) {

    fun getMatrix(): DependencyMatrixDto {
        val columns = columnRepository.findAll()
        val rows = rowRepository.findAll()
        val cells = cellRepository.findAll()
        return DependencyMatrixDto(columns, rows, cells)
    }
}
