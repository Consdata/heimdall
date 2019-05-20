package com.consdata.heimdall.report.add

import com.consdata.heimdall.command.Command
import com.consdata.heimdall.report.ArtifactReport

data class AddReportCommand(val report: ArtifactReport) : Command
