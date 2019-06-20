package com.consdata.heimdall.monitor.overview

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/overview")
internal class DependencyOverviewController(private val repository: DependencyOverviewRepository) {

    @GetMapping
    fun getOverview(): DependencyOverviewDto = DependencyOverviewDto(
            dependencies = repository.findAll().map {
                asDto(it)
            }
    )

    private fun asDto(it: DependencyOverviewEntity): DependencyOverview {
        return DependencyOverview(
                dependency = Dependency(
                        scope = it.dependencyScope,
                        group = it.dependencyGroup,
                        artifact = it.dependencyArtifact,
                        latest = Version(
                                major = it.dependencyLatestMajor,
                                minor = it.dependencyLatestMnior,
                                patch = it.dependencyLatestPath
                        )
                ),
                project = Project(
                        group = it.projectGroup,
                        artifact = it.projectArtifact,
                        version = Version(
                                major = it.projectVersionMajor,
                                minor = it.projectVersionMinor,
                                patch = it.projectVersionPatch
                        )
                ),
                usedVersion = Version(
                        major = it.versionMajor,
                        minor = it.versionMinor,
                        patch = it.versionPatch
                ),
                status = it.status
        )
    }

}
