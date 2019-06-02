package com.consdata.heimdall.dependency.versions

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dependency/versions")
internal class DependencyVersionsController(private val repository: DependencyVersionsRepository) {

    @GetMapping
    fun getDependencies(): List<DependencyVersionEntity> = repository.findAll()

}
