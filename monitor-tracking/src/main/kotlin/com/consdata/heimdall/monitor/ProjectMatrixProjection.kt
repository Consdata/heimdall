package com.consdata.heimdall.monitor

import com.consdata.heimdall.logging.logger
import com.consdata.heimdall.monitor.matrix.DependencyMatrixCellRepository
import com.consdata.heimdall.monitor.matrix.DependencyMatrixColumnRepository
import com.consdata.heimdall.monitor.matrix.DependencyMatrixRowRepository
import com.consdata.heimdall.projections.MultiNodeProjection
import com.consdata.heimdall.report.ReportAddedEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
class ProjectMatrixProjection(
        private val columnRepository: DependencyMatrixColumnRepository,
        private val rowRepository: DependencyMatrixRowRepository,
        private val cellRepository: DependencyMatrixCellRepository
) : MultiNodeProjection {

    override fun projectionName() = "ProjectMatrixProjection"

    private val log by logger()

    @EventHandler
    @Transactional
    fun on(ev: DependencyTrackingAdded) {
        log.info("New dependency tracking [id={}, dependency={}:{}:{}]", ev.id, ev.scope, ev.group, ev.artifact)
        // TODO zapisanie jako matrix column

    }

    @EventHandler
    @Transactional
    fun on(ev: ReportAddedEvent) {
        log.info("New report added [id={}, artifact={}]", ev.id, ev.report.name)
        // TODO zapisanie projektów jako matrix row, wartości jako matrix cell, wersji artefaktu w matrix column (jeśli jest wyższa od aktualnej)

    }

}
