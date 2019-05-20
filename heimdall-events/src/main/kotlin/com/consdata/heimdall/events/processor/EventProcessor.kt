package com.consdata.heimdall.events.processor

import com.consdata.heimdall.events.Event

interface EventProcessor {

    fun after(event: Event)
    fun descriptor(): EventProcessorDescriptor

}
