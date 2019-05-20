package com.consdata.heimdall.report.add

import com.consdata.heimdall.command.CommandBus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/report")
internal class AddReportController(private val commandBus: CommandBus, private val parser: AddReportDtoToArtifactReportParser) {

    @PostMapping("")
    internal fun addDependencyReport(@RequestBody addReportDto: AddReportDto) = commandBus.dispatch(AddReportCommand(
            report = parser.of(addReportDto)
    ))

}
