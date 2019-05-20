package com.consdata.heimdall

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableAsync
@EnableScheduling
class HeimdallApplication

fun main(args: Array<String>) {
	runApplication<HeimdallApplication>(*args) {
		setBannerMode(Banner.Mode.OFF)
	}
}
