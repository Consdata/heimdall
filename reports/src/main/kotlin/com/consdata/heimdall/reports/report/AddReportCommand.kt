package com.consdata.heimdall.reports.report

import com.consdata.heimdall.reports.ArtifactReport
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class AddReportCommand(
        @TargetAggregateIdentifier val id: String = UUID.randomUUID().toString(),
        val report: ArtifactReport
)
