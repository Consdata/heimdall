package com.consdata.heimdall.dependency.list

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dependencies")
internal class DependencyListController(private val repository: DependencyListRepository) {

    @GetMapping
    fun getDependencies(): Iterable<DependencyListEntity> = repository.findAll()

}