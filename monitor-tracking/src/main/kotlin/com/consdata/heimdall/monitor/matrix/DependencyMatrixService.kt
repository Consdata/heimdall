package com.consdata.heimdall.monitor.matrix

import org.springframework.stereotype.Service

@Service
internal class DependencyMatrixService(
        private val columnRepository: DependencyMatrixColumnRepository,
        private val rowRepository: DependencyMatrixRowRepository,
        private val cellRepository: DependencyMatrixCellRepository) {


//    private fun mapRows(entityList: List<DependencyOverviewEntity>): List<DependencyMatrixRowEntity> {
//        val rows = entityList.map {
//            DependencyMatrixRowEntity(
//                    it.projectArtifact,
//                    it.projectGroup,
//                    it.projectId
//            )
//        }
//        return rows.distinct()
//    }
//
//    private fun mapColumns(entityList: List<DependencyOverviewEntity>): List<DependencyMatrixColumnEntity> {
//        val columns = entityList.map {
//            DependencyMatrixColumnEntity(
//                    it.dependencyArtifact,
//                    it.dependencyScope,
//                    it.dependencyGroup,
//                    it.dependencyLatestMajor,
//                    it.dependencyLatestMinor,
//                    it.dependencyLatestPatch)
//        }
//        return columns.distinct()
//    }

    fun getMatrix(): DependencyMatrixDto {
        val columns = columnRepository.findAll()
        val rows = rowRepository.findAll()
        val cells = getCells(columns, rows)
        return DependencyMatrixDto(columns, rows, cells)
    }

    private fun getCells(columns: List<DependencyMatrixColumnEntity>, rows: List<DependencyMatrixRowEntity>): Array<Array<DependencyMatrixCellEntity?>> {
        val matrix = Array(columns.size) {
            Array<DependencyMatrixCellEntity?>(rows.size) {
                null
            }
        }

        cellRepository.findAll().forEach {
            val columnIndex = getColumnIndex(it.dependencyArtifact, columns)
            val rowIndex = getRowIndex(it.projectArtifact, rows)
            val cell = DependencyMatrixCellEntity(
                    it.trackingId,
                    it.dependencyArtifact,
                    it.projectArtifact,
                    it.projectVersionMajor,
                    it.projectVersionMinor,
                    it.projectVersionPatch,
                    it.versionMajor,
                    it.versionMinor,
                    it.versionPatch,
                    it.status
            )
            matrix[columnIndex][rowIndex] = cell
        }
        return matrix
    }

    private fun getColumnIndex(dependencyArtifact: String, columnEntities: List<DependencyMatrixColumnEntity>): Int {
        return columnEntities.indexOf(columnEntities.first { dependencyArtifact == it.dependencyArtifact })
    }

    private fun getRowIndex(projectArtifact: String, rowEntities: List<DependencyMatrixRowEntity>): Int {
        return rowEntities.indexOf(rowEntities.first { projectArtifact == it.projectArtifact })
    }

}
