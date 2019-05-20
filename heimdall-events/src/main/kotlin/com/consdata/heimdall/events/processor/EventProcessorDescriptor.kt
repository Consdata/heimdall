package com.consdata.heimdall.events.processor

data class EventProcessorDescriptor(
        val name: String,
        val version: String = "1",
        val eventBatchSize: Int = 100,
        val eventTypes: List<String> = emptyList()
)