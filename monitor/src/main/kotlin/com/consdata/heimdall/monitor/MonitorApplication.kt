package com.consdata.heimdall.monitor

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
internal class MonitorApplication

fun main(args: Array<String>) {
    runApplication<MonitorApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}
