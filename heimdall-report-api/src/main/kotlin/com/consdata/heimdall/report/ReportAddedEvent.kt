package com.consdata.heimdall.report

import com.google.gson.Gson

data class ReportAddedEvent(val report: ArtifactReport) {
    companion object {
        val type = ReportAddedEvent::class.java.canonicalName!!
        fun fromJson(json: String): ReportAddedEvent = Gson().fromJson(json, ReportAddedEvent::class.java)
    }
}