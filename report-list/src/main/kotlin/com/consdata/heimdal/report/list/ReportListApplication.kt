package com.consdata.heimdal.report.list

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
internal class ReportListApplication

fun main(args: Array<String>) {
    runApplication<ReportListApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}
