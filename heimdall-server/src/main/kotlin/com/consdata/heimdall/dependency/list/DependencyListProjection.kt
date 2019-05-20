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
        val timestamp = event.timestamp
        val reportAdded = ReportAddedEvent.fromJson(event.payload)

        val dependencies = reportAdded.report.modules.flatMap { module ->
            module.dependencies.map {
                DependencyListEntity(
                        timestamp = timestamp,
                        artifactName = it.name.artifact,
                        artifactGroup = it.name.group ?: "",
                        major = it.version.resolved.major.toLong(),
                        minor = it.version.resolved.minor.toLong(),
                        patch = it.version.resolved.patch.toLong()
                )
            }
        }

        dependencies.forEach {
            if (!existsInRepository(it)) {
                repository.save(it)
            }
        }
    }

    private fun existsInRepository(it: DependencyListEntity) =
            repository.existsByArtifactGroupAndArtifactNameAndMajorAndMinorAndPatch(it.artifactGroup, it.artifactName, it.major, it.minor, it.patch)

}