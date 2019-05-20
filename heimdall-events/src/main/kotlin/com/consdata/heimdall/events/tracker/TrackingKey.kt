package com.consdata.heimdall.events.tracker

import com.consdata.heimdall.events.processor.EventProcessorDescriptor

data class TrackingKey(private val name: String, private val version: String) {

    companion object {
        fun ofEventProcessorDescriptor(descriptor: EventProcessorDescriptor) = TrackingKey(
                name = descriptor.name,
                version = descriptor.version
        )
    }

    fun key() = "$name#$version"

}