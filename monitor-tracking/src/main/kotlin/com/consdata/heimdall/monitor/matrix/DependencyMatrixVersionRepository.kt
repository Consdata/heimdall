package com.consdata.heimdall.monitor.matrix

import org.springframework.data.jpa.repository.JpaRepository


interface DependencyMatrixCellRepository : JpaRepository<DependencyMatrixCellEntity, Long>
