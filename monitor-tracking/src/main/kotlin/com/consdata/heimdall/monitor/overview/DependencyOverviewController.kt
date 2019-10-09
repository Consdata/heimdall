package com.consdata.heimdall.monitor.overview

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/overview")
internal class DependencyOverviewController(private val service: DependencyOverviewService) {

    @GetMapping
    fun getOverview(): List<DependencyOverviewEntity> = service.getOverview()

}
