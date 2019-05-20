package com.consdata.heimdall.report.list

import com.consdata.heimdall.events.processor.MappingEventProcessor
import com.consdata.heimdall.events.store.EventStore
import com.consdata.heimdall.events.tracker.jpa.JpaEventTracker
import com.consdata.heimdall.events.tracker.jpa.JpaEventTrackerRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ReportListConfiguration {

    @Bean
    fun reportListProjectionProcessor(repository: JpaEventTrackerRepository, eventStore: EventStore, projection: ReportListProjection) = JpaEventTracker(
            repository,
            eventStore,
            MappingEventProcessor(
                    projection.mapping(),
                    projection.descriptor()
            )

    )

}