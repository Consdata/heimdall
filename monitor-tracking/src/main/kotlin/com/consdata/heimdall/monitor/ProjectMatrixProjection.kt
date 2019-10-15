package com.consdata.heimdall.monitor

import com.consdata.heimdall.logging.logger
import com.consdata.heimdall.monitor.matrix.DependencyMatrixVersionRepository
import com.consdata.heimdall.monitor.matrix.DependencyMatrixDependencyRepository
import com.consdata.heimdall.monitor.matrix.DependencyMatrixProjectRepository
import com.consdata.heimdall.projections.MultiNodeProjection
import com.consdata.heimdall.report.ReportAddedEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
class ProjectMatrixProjection(
        private val dependencyRepository: DependencyMatrixDependencyRepository,
        private val projectRepository: DependencyMatrixProjectRepository,
        private val versionRepository: DependencyMatrixVersionRepository
) : MultiNodeProjection {

    override fun projectionName() = "ProjectMatrixProjection"

    private val log by logger()

    @EventHandler
    @Transactional
    fun on(ev: DependencyTrackingAdded) {
        log.info("New dependency tracking [id={}, dependency={}:{}:{}]", ev.id, ev.scope, ev.group, ev.artifact)
        // TODO zapisanie zalezności do tabeli zawierającej definicję kolumn, jeśli zależność już istnieje to update wersji (dependencyLatest*) jeśli jest nowsza

    }

    @EventHandler
    @Transactional
    fun on(ev: ReportAddedEvent) {
        log.info("New report added [id={}, artifact={}]", ev.id, ev.report.name)
        // TODO zapisanie projektów do tabeli zawierającej definicję wierszy, jeśli projekt już istnieje, to update wersji (projectVersion*) jeśli jest nowsza

        // TODO zapisanie wersji do tabeli zawierającej przecięcia wierszy i kolumn, jeśli istnieje to aktualizacja wersji
        // TODO (niezależnie czy jest nowsza czy nie, bo ktoś mógł zmienić wersję zależności na starszą w nowszej wersji projektu)
    }

}
