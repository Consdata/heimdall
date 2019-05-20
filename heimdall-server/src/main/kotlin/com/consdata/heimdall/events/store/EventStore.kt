package com.consdata.heimdall.events.store

import com.consdata.heimdall.events.Event

interface EventStore {

    fun eventsWindow(offset: Long, limit: Int): List<Event>
    fun add(event: Event): Event
    fun eventsCount(): Number

}