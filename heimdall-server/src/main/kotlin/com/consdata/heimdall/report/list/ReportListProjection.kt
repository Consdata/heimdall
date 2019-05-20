package com.consdata.heimdall.report.list

import com.consdata.heimdall.events.Event
import com.consdata.heimdall.events.processor.EventProcessorDescriptor
import com.consdata.heimdall.events.processor.EventProcessorMapping
import com.consdata.heimdall.report.add.ReportAddedEvent
import org.springframework.stereotype.Component

@Component
class ReportListProjection(private val repository: ReportListRepository) {

    fun descriptor() = EventProcessorDescriptor(
            name = "ReportListProjection",
            eventTypes = listOf(ReportAddedEvent.type)
    )

    fun mapping(): EventProcessorMapping = {
        when (it.payloadType) {
            ReportAddedEvent.type -> this::afterReportAddedEvent
            else -> { _ -> run {} }
        }
    }

    internal fun afterReportAddedEvent(event: Event) {
        val reportAdded = ReportAddedEvent.fromJson(event.payload)
        val artifactName = reportAdded.report.name

        val type = reportAdded.report.type
        val name = artifactName.nameString()
        val version = reportAdded.report.version.versionString()

        repository.save(ReportListEntity(
                artifact = "$type:$name@$version".toLowerCase(),
                timestamp = event.timestamp,
                reportUuid = event.uuid
        ))
    }

}