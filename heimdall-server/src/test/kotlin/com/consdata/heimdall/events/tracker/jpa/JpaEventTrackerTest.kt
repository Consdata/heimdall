package com.consdata.heimdall.events.tracker.jpa

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import com.consdata.heimdall.events.Event
import com.consdata.heimdall.events.processor.EventProcessor
import com.consdata.heimdall.events.processor.EventProcessorDescriptor
import com.consdata.heimdall.events.store.EventStore
import com.consdata.heimdall.events.tracker.TrackingKey
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class JpaEventTrackerTest {

    @MockK
    lateinit var repository: JpaEventTrackerRepository
    @MockK
    lateinit var store: EventStore
    @RelaxedMockK
    lateinit var processor: EventProcessor
    private lateinit var tracker: JpaEventTracker

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        tracker = JpaEventTracker(repository, store, processor)
    }

    @Test
    fun `Should fetch events based on last known offset`() {
        val offset = 17L
        val events = listOf(anEvent())
        val eventProcessorDescriptor = EventProcessorDescriptor("TestEventProcessor", "1.0")
        val trackingKey = TrackingKey.ofEventProcessorDescriptor(eventProcessorDescriptor).key()

        // given
        every { processor.descriptor() } returns eventProcessorDescriptor
        every { repository.findByKey(trackingKey) } returns JpaEventTrackerEntity(trackingKey, offset)
        every { repository.save(any<JpaEventTrackerEntity>()) } returnsArgument 0
        every { store.eventsWindow(offset, eventProcessorDescriptor.eventBatchSize) } returns events

        // when
        tracker.run()

        // then
        verify { processor.after(events) }
    }

    private fun anEvent() = Event(
            uuid = "a",
            version = 1,
            aggregateUuid = "1",
            payload = "",
            payloadType = "",
            timestamp = 1
    )

}
