package com.consdata.heimdall.monitor.matrix

data class DependencyMatrixDto(
        var columnEntities: List<DependencyMatrixColumnEntity>,
        var rowEntities: List<DependencyMatrixRowEntity>,
        var cellEntities: Array<Array<DependencyMatrixCellEntity?>>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DependencyMatrixDto

        if (columnEntities != other.columnEntities) return false
        if (rowEntities != other.rowEntities) return false
        if (!cellEntities.contentDeepEquals(other.cellEntities)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = columnEntities.hashCode()
        result = 31 * result + rowEntities.hashCode()
        result = 31 * result + cellEntities.contentDeepHashCode()
        return result
    }
}
