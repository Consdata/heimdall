package com.consdata.heimdall.events

import com.google.gson.Gson
import java.util.*

class EventTestSupport {

    companion object {

        fun anEvent(payload: Any) = Event(
                uuid = UUID.randomUUID().toString(),
                timestamp = Date().time,
                version = 1,
                aggregateUuid = UUID.randomUUID().toString(),
                payloadType = payload::class.java.canonicalName,
                payload = Gson().toJson(payload)
        )

    }

}