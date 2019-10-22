package com.consdata.heimdall.monitor

import com.consdata.heimdall.logging.logger
import com.consdata.heimdall.monitor.matrix.*
import com.consdata.heimdall.monitor.overview.TrackingStatus
import com.consdata.heimdall.projections.MultiNodeProjection
import com.consdata.heimdall.report.ArtifactDependency
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
        addDependency(ev)
    }

    @EventHandler
    @Transactional
    fun on(ev: ReportAddedEvent) {
        log.info("New report added [id={}, artifact={}]", ev.id, ev.report.name)
        val projectId: Long = addProject(ev)
        val dependencies = ev.report.rootDependencies()
        dependencies.forEach {
            val existingDependency = dependencyRepository.findByDependencyArtifactAndDependencyGroup(it.name, it.group)
            existingDependency?.let { existing ->
                {
                    // Add version only if dependency is tracked
                    addVersion(projectId, existingDependency, it)

                    // Update dependency only if dependency is tracked
                    updateDependency(it, existing)
                }
            }
        }
    }

    private fun updateDependency(it: ArtifactDependency, existing: DependencyMatrixDependencyEntity) {
        if (it.version.major > existing.dependencyLatestMajor ||
                it.version.minor > existing.dependencyLatestMinor ||
                it.version.patch > existing.dependencyLatestPatch) {
            dependencyRepository.save(
                    DependencyMatrixDependencyEntity(
                            dependencyArtifact = existing.dependencyArtifact,
                            dependencyGroup = existing.dependencyGroup,
                            dependencyScope = existing.dependencyScope,
                            dependencyLatestMajor = it.version.major,
                            dependencyLatestMinor = it.version.minor,
                            dependencyLatestPatch = it.version.patch
                    )
            )
        }
    }

    private fun addVersion(projectId: Long, existingDependency: DependencyMatrixDependencyEntity, it: ArtifactDependency) {
        versionRepository.save(
                DependencyMatrixVersionEntity(
                        projectId = projectId,
                        dependencyId = existingDependency.dependencyId!!,
                        versionMajor = it.version.major,
                        versionMinor = it.version.minor,
                        versionPatch = it.version.patch,
                        status = TrackingStatus.Unknown // TODO
                )
        )
    }

    private fun addProject(ev: ReportAddedEvent): Long {
        val existingProject = projectRepository.findByProjectArtifactAndProjectGroup(ev.report.name.artifact, ev.report.name.group)
        val projectFromRaport = DependencyMatrixProjectEntity(
                projectArtifact = ev.report.name.artifact,
                projectGroup = ev.report.name.group,
                projectVersionMajor = ev.report.version.major,
                projectVersionMinor = ev.report.version.minor,
                projectVersionPatch = ev.report.version.patch
        )
        if (existingProject == null) {
            // Add project if not exist
            return projectRepository.save(projectFromRaport).projectId!!
        } else {
            // Update project if exist and is newer
            if (projectFromRaport.projectVersionMajor > existingProject.projectVersionMajor ||
                    projectFromRaport.projectVersionMinor > existingProject.projectVersionMinor ||
                    projectFromRaport.projectVersionPatch > existingProject.projectVersionPatch) {
                projectRepository.delete(existingProject)
                projectFromRaport.projectId = existingProject.projectId
                projectRepository.delete(existingProject)
                return projectRepository.save(projectFromRaport).projectId!!
            }
            return existingProject.projectId!!
        }
    }

    private fun addDependency(ev: DependencyTrackingAdded) {
        val existingDependency = dependencyRepository.findByDependencyArtifactAndDependencyGroup(ev.artifact, ev.group)
        if (existingDependency == null) {
            // Add dependency only if not exist
            dependencyRepository.save(
                    DependencyMatrixDependencyEntity(
                            dependencyArtifact = ev.artifact,
                            dependencyGroup = ev.group,
                            dependencyScope = ev.scope,
                            dependencyLatestMajor = 0,
                            dependencyLatestMinor = 0,
                            dependencyLatestPatch = 0
                    )
            )
        }
    }
}
