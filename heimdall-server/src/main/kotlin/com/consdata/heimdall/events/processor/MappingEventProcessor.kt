package com.consdata.heimdall.events.processor

import com.consdata.heimdall.events.Event
import com.consdata.heimdall.logging.logger

typealias EventProcessorMapping = (Event) -> (Event) -> Unit

class MappingEventProcessor(val mapper: EventProcessorMapping, private val descriptor: EventProcessorDescriptor) : EventProcessor {

    private val log by logger()

    override fun descriptor() = descriptor

    override fun after(events: List<Event>) = events.forEach {
        try {
            mapper(it)(it)
        } catch (exception: Exception) {
            log.error("Exception while handling event [uuid={}, type={}]", it.uuid, it.payloadType, exception)
        }
    }

}
