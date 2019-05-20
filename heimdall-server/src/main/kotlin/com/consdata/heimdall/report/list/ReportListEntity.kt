package com.consdata.heimdall.report.list

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ReportListEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
        val artifact: String,
        val timestamp: Long,
        val reportUuid: String
)
