package com.consdata.heimdall.monitor.overview.matrix

data class DependencyOverviewMatrix(
        var columns: List<DependencyOverviewMatrixColumn>,
        var rows: List<DependencyOverviewMatrixRow>,
        var matrix: Array<Array<DependencyOverviewMatrixCell?>>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DependencyOverviewMatrix

        if (columns != other.columns) return false
        if (rows != other.rows) return false
        if (!matrix.contentDeepEquals(other.matrix)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = columns.hashCode()
        result = 31 * result + rows.hashCode()
        result = 31 * result + matrix.contentDeepHashCode()
        return result
    }
}
