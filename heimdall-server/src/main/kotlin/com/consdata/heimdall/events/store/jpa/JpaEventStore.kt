package com.consdata.heimdall.events.store.jpa

import com.consdata.heimdall.events.Event
import com.consdata.heimdall.events.store.EventStore
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class JpaEventStore(private val repository: JpaEventRepository) : EventStore {

    override fun add(event: Event): Event {
        val entity = JpaEventEntity.ofEvent(event)
        repository.save(entity)
        return event.copy(
                order = entity.sequence
        )
    }

    override fun eventsWindow(offset: Long, limit: Int): List<Event> = repository
            .findBySequenceGreaterThanOrderBySequenceAsc(offset, PageRequest.of(0, limit))
            .map { it.asEvent() }

    override fun eventsCount() = repository.count()

}