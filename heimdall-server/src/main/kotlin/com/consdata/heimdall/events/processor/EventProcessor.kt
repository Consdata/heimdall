package com.consdata.heimdall.events.processor

import com.consdata.heimdall.events.Event

interface EventProcessor {

    fun after(events: List<Event>)
    fun descriptor(): EventProcessorDescriptor

}
