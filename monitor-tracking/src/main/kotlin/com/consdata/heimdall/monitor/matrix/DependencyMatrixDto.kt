package com.consdata.heimdall.monitor.matrix

data class DependencyMatrixDto(
        var columnEntities: List<DependencyMatrixColumnEntity>,
        var rowEntities: List<DependencyMatrixRowEntity>,
        var cellEntities: List<DependencyMatrixCellEntity>
)
