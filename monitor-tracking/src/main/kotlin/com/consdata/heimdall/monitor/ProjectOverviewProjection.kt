package com.consdata.heimdall.monitor

import com.consdata.heimdall.monitor.tracking.TrackedDependencyEntity
import com.consdata.heimdall.monitor.tracking.TrackedDependencyRepository
import com.consdata.heimdall.report.ReportAddedEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
@Transactional
class ProjectOverviewProjection(
        private val tracking: TrackedDependencyRepository
) {

    fun on(ev: ReportAddedEvent) {
        // if module newer than already stored else skip
        // drop previous deps for module
        // store new deps list
    }

//    @EventHandler
//    fun on(ev: ReportAddedEvent) {
//        val scope = ev.report.type.toString()
//        val entities = ev.report
//                .modules
//                // filter by module from report with higher version then already stored in db
//                .filter { true }
//                .flatMap { it.dependencies }
//                .filter { dependencyRepository.existsByScopeAndGroupAndArtifact(scope, it.name.group, it.name.artifact) }
//                .map {
//                    ProjectDependencyEntity(
//
//                    )
//                }
//        projectRepository.saveAll(entities)
//    }

    @EventHandler
    fun on(ev: DependencyTrackingAdded) {
        val entity = TrackedDependencyEntity(
                groupId = ev.group,
                artifact = ev.artifact,
                scope = ev.scope
        )
        if (tracking.existsByScopeAndGroupAndArtifact(entity.scope, entity.groupId, entity.artifact)) {
            tracking.save(entity)
        }
    }

}
