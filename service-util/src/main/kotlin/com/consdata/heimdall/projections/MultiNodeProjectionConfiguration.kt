package com.consdata.heimdall.projections

import org.axonframework.config.EventProcessingModule
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
open class MultiNodeProjectionConfiguration : InitializingBean {

    @Autowired
    lateinit var eventProcessingModule: EventProcessingModule

    @Autowired
    lateinit var applicationInstanceUuid: ApplicationInstanceUuid

    @Value("\${spring.application.name}")
    lateinit var applicationName: String

    override fun afterPropertiesSet() {
        eventProcessingModule.byDefaultAssignHandlerInstancesTo { o -> processingGroupMapper(o) }
    }

    private fun processingGroupMapper(handler: Any) =
            if (handler is MultiNodeProjection) "$applicationName:${handler.projectionName()}:${applicationInstanceUuid.uuid()}" else handler.javaClass.getPackage().name

}