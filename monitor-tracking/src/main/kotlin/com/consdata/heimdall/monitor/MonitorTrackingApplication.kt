package com.consdata.heimdall.monitor

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
internal class MonitorTrackingApplication

fun main(args: Array<String>) {
    runApplication<MonitorTrackingApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}
