package com.consdata.heimdall.monitor.overview

import com.consdata.heimdall.monitor.ArtifactScope
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class AddDependencyTrackingCommand(
        @TargetAggregateIdentifier val id: String = UUID.randomUUID().toString(),
        val group: String?,
        val artifact: String,
        val scope: ArtifactScope
)
