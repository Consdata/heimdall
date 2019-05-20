package com.consdata.heimdall.events

data class Event(
        val uuid: String,
        val aggregateUuid: String,
        val version: Int,
        val timestamp: Long,
        val payloadType: String,
        val payload: String,
        val order: Number? = null
)
