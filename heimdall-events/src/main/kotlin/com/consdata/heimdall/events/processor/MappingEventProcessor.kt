package com.consdata.heimdall.events.processor

import com.consdata.heimdall.events.Event

typealias EventProcessorMapping = (Event) -> (Event) -> Unit

class MappingEventProcessor(val mapper: EventProcessorMapping, private val descriptor: EventProcessorDescriptor) : EventProcessor {

    override fun descriptor() = descriptor

    override fun after(event: Event) = mapper(event)(event)

}
