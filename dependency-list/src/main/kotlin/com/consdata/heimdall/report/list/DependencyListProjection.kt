package com.consdata.heimdall.report.list

import com.consdata.heimdall.logging.logger
import com.consdata.heimdall.report.ReportAddedEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
internal class DependencyListProjection(private val repository: DependencyRepository) {

    private val log by logger()

    @EventHandler
    @Transactional
    fun on(ev: ReportAddedEvent) {
        log.info("New report added [id={}, artifact={}]", ev.id, ev.report.name)

        val dependencies = ev
                .report
                .dependencies
                .map {
                    DependencyEntity(
                            scope = ev.report.type.name,
                            groupId = it.name.group ?: "",
                            artifactId = it.name.artifact
                    )
                }
                .filter {
                    !repository.existsByScopeAndGroupIdAndArtifactId(it.scope, it.groupId, it.artifactId)
                }
        repository.saveAll(dependencies)
    }

}
