package com.consdata.heimdall.events.processor

import com.consdata.heimdall.events.Event
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MappingEventProcessorTest {

    @Test
    fun `Should parse mapping definition`() {
        // given
        var captured: Event? = null
        val processor = MappingEventProcessor(
                { mappingEvent ->
                    when (mappingEvent.payloadType) {
                        "ReportAdded" -> { event -> captured = event }
                        else -> { _ -> run {} }
                    }
                },
                EventProcessorDescriptor(
                        name = "Test"
                )
        )

        // when
        processor.after(oneEvent())

        // then
        assertThat(captured).isNotNull
    }

    private fun oneEvent(): List<Event> = listOf(Event(
            uuid = "1",
            aggregateUuid = "1",
            version = 1,
            timestamp = 1,
            payloadType = "ReportAdded",
            payload = "hello event"
    ))

}
