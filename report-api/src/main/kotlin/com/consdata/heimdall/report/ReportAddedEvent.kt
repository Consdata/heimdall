package com.consdata.heimdall.report

data class ReportAddedEvent(val id: String, val timestamp: Long, val report: ArtifactReport)
