package com.consdata.heimdall.monitor

import com.consdata.heimdall.logging.logger
import com.consdata.heimdall.monitor.overview.DependencyOverviewEntity
import com.consdata.heimdall.monitor.overview.DependencyOverviewRepository
import com.consdata.heimdall.monitor.overview.TrackingStatus
import com.consdata.heimdall.monitor.project.ProjectEntity
import com.consdata.heimdall.monitor.project.ProjectRepository
import com.consdata.heimdall.monitor.tracking.TrackedDependencyEntity
import com.consdata.heimdall.monitor.tracking.TrackedDependencyRepository
import com.consdata.heimdall.projections.MultiNodeProjection
import com.consdata.heimdall.report.*
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component
import java.lang.IllegalStateException
import javax.transaction.Transactional

@Component
class ProjectOverviewProjection(
        private val tracking: TrackedDependencyRepository,
        private val projects: ProjectRepository,
        private val dependencies: DependencyOverviewRepository
) : MultiNodeProjection {

    override fun projectionName() = "ProjectOverviewProjection"

    private val log by logger()

    @EventHandler
    @Transactional
    fun on(ev: DependencyTrackingAdded) {
        log.info("New dependency tracking [id={}, dependency={}:{}:{}]", ev.id, ev.scope, ev.group, ev.artifact)

        val entity = TrackedDependencyEntity(
                groupId = ev.group,
                artifact = ev.artifact,
                scope = ev.scope,
                latestMajor = 0,
                latestMinor = 0,
                latestPatch = 0
        )
        if (!tracking.existsByScopeAndGroupIdAndArtifact(entity.scope, entity.groupId, entity.artifact)) {
            tracking.save(entity)
        }
    }

    @EventHandler
    @Transactional
    fun on(ev: ReportAddedEvent) {
        log.info("New report added [id={}, artifact={}]", ev.id, ev.report.name)

        val report = ev.report
        val artifactScope = asArtifactScope(report.type)

        val existingProject = projects.findByScopeAndGroupIdAndArtifact(
                artifactScope,
                report.name.group,
                report.name.artifact
        )
        val project = asProjectEntity(artifactScope, report)

        updateKnownTrackedDependencies(report, artifactScope)
        if (existingProject == null || project.compare(existingProject) >= 0) {
            updateProjectDependencyTracking(report, artifactScope, project, existingProject)
        }
    }

    private fun updateKnownTrackedDependencies(report: ArtifactReport, artifactScope: ArtifactScope) {
        report.rootDependencies()
                .filter { tracking.existsByScopeAndGroupIdAndArtifact(artifactScope, it.group, it.name) }
                .forEach {
                    val existing = tracking.findByScopeAndGroupIdAndArtifact(artifactScope, it.group, it.name)
                    if (existing.compare(it.version.major, it.version.minor, it.version.patch) < 0) {
                        existing.setVersion(it.version.major, it.version.minor, it.version.patch)
                        tracking.save(existing)
                        dependencies.setLatestDependency(
                                it.version.major, it.version.minor, it.version.patch,
                                existing.id ?: throw IllegalStateException("Missing tracking id")
                        )
                    }
                }
    }

    private fun updateProjectDependencyTracking(report: ArtifactReport, artifactScope: ArtifactScope, project: ProjectEntity, existingProject: ProjectEntity?) {
        if (existingProject == null) {
            projects.save(project)
        } else {
            existingProject.id?.let { dependencies.deleteByProjectId(it) }
            existingProject.setVersion(project.versionMajor, project.versionMinor, project.versionPatch)
            projects.save(existingProject)
        }

        val projectId = existingProject?.id ?: project.id ?: throw IllegalStateException("Missing project id!")

        val projectDependencies = report.rootDependencies()
                .filter { tracking.existsByScopeAndGroupIdAndArtifact(artifactScope, it.group, it.name) }
                .map {
                    asOverviewEntity(projectId, artifactScope, it, project)
                }
        dependencies.saveAll(projectDependencies)
    }

    private fun asOverviewEntity(projectId: Long, artifactScope: ArtifactScope, it: ArtifactDependency, project: ProjectEntity): DependencyOverviewEntity {
        val tracking = tracking.findByScopeAndGroupIdAndArtifact(artifactScope, it.group, it.name)
        return DependencyOverviewEntity(
                projectId = projectId,
                trackingId = tracking.id ?: throw IllegalStateException("Missing tracking id"),
                dependencyScope = artifactScope,
                dependencyGroup = it.group,
                dependencyArtifact = it.name,
                dependencyLatestMajor = tracking.latestMajor,
                dependencyLatestMinor = tracking.latestMinor,
                dependencyLatestPatch = tracking.latestPatch,
                projectGroup = project.groupId,
                projectArtifact = project.artifact,
                projectVersionMajor = project.versionMajor,
                projectVersionMinor = project.versionMinor,
                projectVersionPatch = project.versionPatch,
                versionMajor = it.version.major,
                versionMinor = it.version.minor,
                versionPatch = it.version.patch,
                status = dependencyStatus(it.version, tracking)
        )
    }

    private fun dependencyStatus(version: ArtifactVersion, tracking: TrackedDependencyEntity) = when (tracking.compare(version.major, version.minor, version.patch)) {
        1 -> TrackingStatus.Outdated
        else -> TrackingStatus.Current
    }

    private fun asProjectEntity(artifactScope: ArtifactScope, report: ArtifactReport): ProjectEntity {
        return ProjectEntity(
                scope = artifactScope,
                groupId = report.name.group,
                artifact = report.name.artifact,
                versionMajor = report.version.major,
                versionMinor = report.version.minor,
                versionPatch = report.version.patch
        )
    }

    private fun asArtifactScope(type: ArtifactType) = when (type) {
        ArtifactType.Npm -> ArtifactScope.Npm
        ArtifactType.Maven -> ArtifactScope.Maven
        else -> throw IllegalArgumentException("Unknown scope name")
    }

}
