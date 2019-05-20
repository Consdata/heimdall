package com.consdata.heimdall.events.processor

import com.consdata.heimdall.events.Event

// na podstawie typu event stara sie dopasowac refleksją metode delegata
// i wywoluje ją gdy znajdzie
class ReflectiveEventProcessor(private val deletage: Any) : EventProcessor {

    override fun descriptor(): EventProcessorDescriptor {
        TODO("not implemented")
    }

    override fun after(event: Event) {
        TODO("not implemented")
    }

}
