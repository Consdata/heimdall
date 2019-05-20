package com.consdata.heimdall.events.tracker.jpa

import com.consdata.heimdall.events.processor.EventProcessor
import com.consdata.heimdall.events.processor.EventProcessorDescriptor
import com.consdata.heimdall.events.store.EventStore
import com.consdata.heimdall.events.tracker.EventTracker
import com.consdata.heimdall.events.tracker.TrackingKey
import javax.transaction.Transactional

open class JpaEventTracker(
        private val repository: JpaEventTrackerRepository,
        private val eventStore: EventStore,
        private val eventProcessor: EventProcessor) : EventTracker {

    @Transactional
    override fun run() {
        val eventProcessorKey = eventProcessorKey(eventProcessor.descriptor())
        val tracking = repository.findByKey(eventProcessorKey)
        val offset = tracking?.lastOffset ?: 0

        val events = events(offset, batchSize())

        eventProcessor.after(events)
        repository.save(JpaEventTrackerEntity(
                id = tracking?.id,
                key = eventProcessorKey,
                lastOffset = offset + events.size
        ))
    }

    private fun events(offset: Long, limit: Int) = eventStore.eventsWindow(offset, limit)

    private fun batchSize() = eventProcessor.descriptor().eventBatchSize

    private fun eventProcessorKey(descriptor: EventProcessorDescriptor) = TrackingKey(descriptor.name, descriptor.version).key()

}
