package com.consdata.heimdall.report.list

import com.consdata.heimdall.projections.MultiNodeProjectionConfiguration
import com.consdata.heimdall.projections.EnvironmentApplicationInstanceUuid
import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(MultiNodeProjectionConfiguration::class)
internal class ReportListApplication {

    @Bean
    fun applicationInstanceUuid() = EnvironmentApplicationInstanceUuid()

}

fun main(args: Array<String>) {
    runApplication<ReportListApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}
