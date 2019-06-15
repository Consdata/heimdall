package com.consdata.heimdall.report

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
internal class ReportApplication

fun main(args: Array<String>) {
    runApplication<ReportApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}
