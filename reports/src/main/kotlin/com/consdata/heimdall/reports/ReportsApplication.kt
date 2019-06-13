package com.consdata.heimdall.reports

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
internal class ReportsApplication

fun main(args: Array<String>) {
    runApplication<ReportsApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}
