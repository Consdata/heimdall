package com.consdata.heimdall.monitor.overview

import com.consdata.heimdall.monitor.overview.matrix.DependencyOverviewMatrix
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/overview")
internal class DependencyOverviewController(private val service: DependencyOverviewService) {

    @GetMapping
    fun getOverview(): List<DependencyOverviewEntity> = service.getOverview()

    @GetMapping("/matrix")
    fun getOverviewMatrix(): DependencyOverviewMatrix = service.getOverviewMatrix()

}
