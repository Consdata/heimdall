package com.consdata.heimdall.reports.add

import com.consdata.heimdall.reports.report.AddReportCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reports")
internal class AddReportController(
        private val commandGateway: CommandGateway,
        private val parser: AddReportDtoToArtifactReportParser
) {

    @PostMapping("")
    internal fun addDependencyReport(@RequestBody addReportDto: AddReportDto) = commandGateway.sendAndWait<String>(AddReportCommand(
            report = parser.of(addReportDto)
    ))

}
