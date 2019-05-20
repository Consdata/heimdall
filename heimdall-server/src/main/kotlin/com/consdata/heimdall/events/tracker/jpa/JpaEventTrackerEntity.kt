package com.consdata.heimdall.events.tracker.jpa

import javax.persistence.*

@Entity
@Table(
        indexes = [
            Index(columnList = "key")
        ]
)
data class JpaEventTrackerEntity(
        var key: String,
        var lastOffset: Long,
        @Id @GeneratedValue var id: Long? = null
)