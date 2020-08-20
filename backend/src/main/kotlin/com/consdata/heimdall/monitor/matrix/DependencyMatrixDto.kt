package com.consdata.heimdall.monitor.matrix

data class DependencyMatrixDto(
        var dependencyEntities: List<DependencyMatrixDependencyEntity>,
        var projectEntities: List<DependencyMatrixProjectEntity>,
        var versionEntities: List<DependencyMatrixVersionEntity>
)
