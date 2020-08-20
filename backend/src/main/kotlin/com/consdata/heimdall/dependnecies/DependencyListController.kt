package com.consdata.heimdall.dependnecies

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
internal class DependencyListController(
        private val repository: DependencyRepository,
        private val search: DependencySearchService
) {

    @GetMapping
    fun getAll(): List<DependencyEntity> = repository.findAll()

    @PostMapping
    fun search(@RequestBody request: SearchRequest) = search.search(request.query)

}
