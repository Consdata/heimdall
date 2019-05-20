package com.consdata.heimdall.events.tracker.jpa

import com.consdata.heimdall.events.store.EventStore
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

internal data class EventTrackerStatusDto(
        val trackers: Iterable<JpaEventTrackerEntity>,
        val events: Number
)

@RestController
@RequestMapping("/event-tracker")
internal class JpaEventTrackerController(private val repository: JpaEventTrackerRepository, private val store: EventStore) {

    @RequestMapping("/status")
    fun getStatus() = EventTrackerStatusDto(
            trackers = repository.findAll(),
            events = store.eventsCount()
    )

}