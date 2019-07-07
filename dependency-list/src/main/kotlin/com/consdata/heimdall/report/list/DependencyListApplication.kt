package com.consdata.heimdall.report.list

import com.consdata.heimdall.projections.EnvironmentApplicationInstanceUuid
import com.consdata.heimdall.projections.MultiNodeProjectionConfiguration
import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import


@SpringBootApplication
@Import(MultiNodeProjectionConfiguration::class)
internal class DependencyListApplication {

    @Bean
    fun applicationInstanceUuid() = EnvironmentApplicationInstanceUuid()

}

fun main(args: Array<String>) {
    runApplication<DependencyListApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}
