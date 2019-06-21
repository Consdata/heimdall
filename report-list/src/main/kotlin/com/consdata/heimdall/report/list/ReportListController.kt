package com.consdata.heimdall.report.list

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/report")
internal class ReportListController(private val repository: ReportListRepository) {

    @GetMapping
    fun getReportList(): Iterable<ReportListEntity> = repository.findAll()

}
