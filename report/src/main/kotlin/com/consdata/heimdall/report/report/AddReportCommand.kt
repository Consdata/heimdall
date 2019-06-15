package com.consdata.heimdall.report.report

import com.consdata.heimdall.report.ArtifactReport
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class AddReportCommand(
        @TargetAggregateIdentifier val id: String = UUID.randomUUID().toString(),
        val report: ArtifactReport
)
