package com.consdata.heimdall.report.add

import com.google.gson.Gson
import com.consdata.heimdall.report.ArtifactReport

data class ReportAddedEvent(val report: ArtifactReport) {
    companion object {
        val type = ReportAddedEvent::class.java.canonicalName!!
        fun fromJson(json: String): ReportAddedEvent = Gson().fromJson(json, ReportAddedEvent::class.java)
    }
}