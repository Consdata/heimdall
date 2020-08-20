package com.consdata.heimdall

import com.consdata.heimdall.projections.EnvironmentApplicationInstanceUuid
import com.consdata.heimdall.projections.MultiNodeProjectionConfiguration
import com.consdata.heimdall.report.ReportAddedEventV2Upcast
import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(MultiNodeProjectionConfiguration::class)
internal class HeimdallApplication {

    @Bean
    fun applicationInstanceUuid() = EnvironmentApplicationInstanceUuid()

    @Bean
    fun reportAddedV2Upcaster() = ReportAddedEventV2Upcast()

}

fun main(args: Array<String>) {
    runApplication<HeimdallApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}
