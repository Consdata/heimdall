package com.consdata.heimdall.monitor.add

import com.consdata.heimdall.monitor.ArtifactScope
import com.consdata.heimdall.monitor.overview.AddDependencyTrackingCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/monitor/tracking")
internal class AddDependencyTrackingController(
        private val commandGateway: CommandGateway
) {

    @PostMapping
    internal fun addDependencyTracking(@RequestBody dto: AddDependencyTrackingDto) = commandGateway.sendAndWait<String>(AddDependencyTrackingCommand(
            group = dto.group,
            artifact = dto.artifact,
            scope = asScope(dto.scope)
    ))

    private fun asScope(scope: String) = when (scope) {
        "Npm" -> ArtifactScope.Npm
        "Maven" -> ArtifactScope.Maven
        else -> throw IllegalArgumentException("Unknown scope name")
    }

}
