package com.consdata.heimdall.monitor.overview

import com.consdata.heimdall.monitor.DependencyTrackingAdded
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
internal class DependencyTracking {

    @AggregateIdentifier
    private lateinit var id: String

    @CommandHandler
    constructor(cmd: AddDependencyTrackingCommand) {
        AggregateLifecycle.apply(DependencyTrackingAdded(
                id = cmd.id,
                group = cmd.group,
                artifact = cmd.artifact,
                scope = cmd.scope
        ))
    }

    @EventSourcingHandler
    fun on(ev: DependencyTrackingAdded) {
        id = ev.id
    }

}
