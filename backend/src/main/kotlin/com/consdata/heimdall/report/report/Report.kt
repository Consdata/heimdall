package com.consdata.heimdall.report.report

import com.consdata.heimdall.report.ReportAddedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
internal class Report {

    @AggregateIdentifier
    private lateinit var id: String

    @CommandHandler
    constructor(cmd: AddReportCommand) {
        AggregateLifecycle.apply(ReportAddedEvent(
                id = cmd.id,
                report = cmd.report,
                timestamp = System.currentTimeMillis()
        ))
    }

    @EventSourcingHandler
    fun on(ev: ReportAddedEvent) {
        id = ev.id
    }


}
