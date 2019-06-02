package com.consdata.heimdall.dependency.list

import com.consdata.heimdall.events.Event
import com.consdata.heimdall.events.processor.EventProcessorDescriptor
import com.consdata.heimdall.events.processor.EventProcessorMapping
import com.consdata.heimdall.report.ReportAddedEvent
import org.springframework.stereotype.Component

@Component
class DependencyListProjection(private val repository: DependencyListRepository) {

    fun descriptor() = EventProcessorDescriptor(
            name = "DependencyListProjection",
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
        val dependencyScope = DependencyScope.ofReportType(reportAdded.report.type)

        val dependencies = reportAdded.report.modules.flatMap { module ->
            module.dependencies.map {
                DependencyEntity(
                        scope = dependencyScope,
                        artifactId = it.name.artifact,
                        groupId = it.name.group ?: ""
                )
            }
        }

        dependencies.forEach {
            if (!existsInRepository(it)) {
                repository.save(it)
            }
        }
    }

    private fun existsInRepository(it: DependencyEntity) =
            repository.existsByScopeAndGroupIdAndArtifactId(it.scope, it.groupId, it.artifactId)

}
