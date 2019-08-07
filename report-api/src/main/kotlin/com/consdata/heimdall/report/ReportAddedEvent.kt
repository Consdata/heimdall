package com.consdata.heimdall.report

import org.axonframework.serialization.Revision

@Revision("2")
data class ReportAddedEvent(val id: String, val timestamp: Long, val report: ArtifactReport)
