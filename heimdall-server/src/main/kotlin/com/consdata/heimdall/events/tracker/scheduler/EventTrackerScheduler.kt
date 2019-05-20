package com.consdata.heimdall.events.tracker.scheduler

import com.consdata.heimdall.events.tracker.EventTracker
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class EventTrackerScheduler(private val trackers: List<EventTracker>) : Runnable {

    @Scheduled(fixedDelayString = "1000")
    override fun run() = trackers.forEach(EventTracker::run)

}