package com.consdata.heimdall.events.store.jpa

import com.consdata.heimdall.events.Event
import java.util.*
import javax.persistence.*

@Entity
@Table(indexes = [Index(name = "idx_event_entity_uuid", columnList = "uuid")])
data class JpaEventEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val sequence: Long = 0,
        val uuid: String = UUID.randomUUID().toString(),
        val aggregateUuid: String,
        val version: Int,
        val timestamp: Long,
        val payloadType: String,
        @Lob @Basic(fetch = FetchType.LAZY) val payload: String
) {

    companion object {
        fun ofEvent(event: Event) = JpaEventEntity(
                uuid = event.uuid,
                aggregateUuid = event.aggregateUuid,
                version = event.version,
                timestamp = event.timestamp,
                payloadType = event.payloadType,
                payload = event.payload
        )
    }

    fun asEvent() = Event(
            uuid = uuid,
            aggregateUuid = aggregateUuid,
            version = version,
            timestamp = timestamp,
            payloadType = payloadType,
            payload = payload,
            order = sequence
    )

}