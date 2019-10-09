package com.consdata.heimdall.monitor.matrix

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/matrix")
internal class DependencyMatrixController(private val service: DependencyMatrixService) {

    @GetMapping("/matrix")
    fun getOverviewMatrix(): DependencyMatrixDto = service.getMatrix()

}
