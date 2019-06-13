package com.consdata.heimdal.reports.list

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
internal class ReportsListApplication

fun main(args: Array<String>) {
    runApplication<ReportsListApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}
