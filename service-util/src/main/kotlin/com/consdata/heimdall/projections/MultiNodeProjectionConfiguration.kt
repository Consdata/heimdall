package com.consdata.heimdall.projections

import org.axonframework.config.EventProcessingModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
open class MultiNodeProjectionConfiguration {

    @Autowired
    lateinit var eventProcessingModule: EventProcessingModule

    @Autowired
    lateinit var applicationInstanceUuid: ApplicationInstanceUuid

    @Value("\${spring.application.name}")
    lateinit var applicationName: String

    @PostConstruct
    fun overrideMultiNodeProjectionGroupNames() {
        eventProcessingModule.assignProcessingGroup { processingGroup: String -> "$applicationName.$processingGroup.${applicationInstanceUuid.uuid()}"}
    }

}