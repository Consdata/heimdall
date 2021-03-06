package com.consdata.heimdall.report.list

import com.consdata.heimdall.logging.logger
import com.consdata.heimdall.projections.MultiNodeProjection
import com.consdata.heimdall.report.ReportAddedEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
internal class ReportListProjection(private val repository: ReportListRepository) : MultiNodeProjection {

    override fun projectionName() = "ReportListProjection"

    private val log by logger()

    @EventHandler
    @Transactional
    fun on(ev: ReportAddedEvent) {
        log.info("New report added [id={}, artifact={}]", ev.id, ev.report.name)

        val artifactName = ev.report.name
        val type = ev.report.type
        val name = artifactName.nameString()
        val version = ev.report.version.versionString()

        repository.save(ReportListEntity(
                artifact = "$type:$name:$version".toLowerCase(),
                timestamp = ev.timestamp,
                reportUuid = ev.id
        ))
    }

}
