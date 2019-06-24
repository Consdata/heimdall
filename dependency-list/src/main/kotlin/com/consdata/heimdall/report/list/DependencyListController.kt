package com.consdata.heimdall.report.list

import org.springframework.web.bind.annotation.*

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
