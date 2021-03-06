package com.consdata.heimdall.monitor.matrix

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/matrix")
internal class DependencyMatrixController(private val service: DependencyMatrixService) {

    @GetMapping
    fun getOverviewMatrix(): DependencyMatrixDto = service.getMatrix()

}
