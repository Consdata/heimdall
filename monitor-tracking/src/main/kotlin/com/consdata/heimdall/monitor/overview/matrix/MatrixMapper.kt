package com.consdata.heimdall.monitor.overview.matrix

import com.consdata.heimdall.monitor.overview.DependencyOverviewEntity
import org.springframework.stereotype.Component

@Component
internal class MatrixMapper {
    fun map(entityList: List<DependencyOverviewEntity>): DependencyOverviewMatrix {
        val columns = mapColumns(entityList)
        val rows = mapRows(entityList)
        val matrix = mapMatrix(entityList, columns, rows)
        return DependencyOverviewMatrix(columns, rows, matrix)
    }

    private fun mapMatrix(
            entityList: List<DependencyOverviewEntity>,
            columns: List<DependencyOverviewMatrixColumn>,
            rows: List<DependencyOverviewMatrixRow>
    ): Array<Array<DependencyOverviewMatrixCell?>> {
        val matrix = Array(columns.size) {
            Array<DependencyOverviewMatrixCell?>(rows.size) {
                null
            }
        }
        entityList.forEach {
            val columnIndex = getColumnIndex(it.dependencyArtifact, columns)
            val rowIndex = getRowIndex(it.projectArtifact, rows)
            val cell = DependencyOverviewMatrixCell(
                    it.trackingId,
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

    private fun mapRows(entityList: List<DependencyOverviewEntity>): List<DependencyOverviewMatrixRow> {
        val rows = entityList.map {
            DependencyOverviewMatrixRow(
                    it.projectGroup,
                    it.projectArtifact,
                    it.projectId
            )
        }
        return rows.distinct()
    }

    private fun mapColumns(entityList: List<DependencyOverviewEntity>): List<DependencyOverviewMatrixColumn> {
        val columns = entityList.map {
            DependencyOverviewMatrixColumn(
                    it.dependencyScope,
                    it.dependencyGroup,
                    it.dependencyArtifact,
                    it.dependencyLatestMajor,
                    it.dependencyLatestMinor,
                    it.dependencyLatestPatch)
        }
        return columns.distinct()
    }

    private fun getColumnIndex(dependencyArtifact: String, columns: List<DependencyOverviewMatrixColumn>): Int {
        return columns.indexOf(columns.first { dependencyArtifact == it.dependencyArtifact })
    }

    private fun getRowIndex(projectArtifact: String, rows: List<DependencyOverviewMatrixRow>): Int {
        return rows.indexOf(rows.first { projectArtifact == it.projectArtifact })
    }
}
