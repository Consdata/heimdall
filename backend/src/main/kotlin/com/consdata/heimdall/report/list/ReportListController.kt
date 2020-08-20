package com.consdata.heimdall.report.list

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/report")
internal class ReportListController(private val repository: ReportListRepository) {

    @GetMapping("/count")
    fun getCount(): Long = repository.count()

    @GetMapping
    fun getReportList(): ReportListDto = ReportListDto(
            repository.findAll()
    )

}
