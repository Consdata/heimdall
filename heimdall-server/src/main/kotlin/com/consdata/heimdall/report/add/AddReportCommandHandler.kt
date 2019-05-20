package com.consdata.heimdall.report.add

import com.google.gson.Gson
import com.consdata.heimdall.command.Command
import com.consdata.heimdall.command.CommandHandler
import com.consdata.heimdall.command.CommandHandlerException
import com.consdata.heimdall.events.Event
import com.consdata.heimdall.events.store.EventStore
import com.consdata.heimdall.logging.logger
import com.consdata.heimdall.report.ReportAddedEvent
import org.springframework.stereotype.Component
import java.util.*

@Component
class AddReportCommandHandler(private val store: EventStore) : CommandHandler {

    private val log by logger()

    override fun supported() = listOf(AddReportCommand::class.java)

    override fun on(command: Command) = when (command.javaClass) {
        AddReportCommand::class.java -> onAddReportCommand(command as AddReportCommand)
        else -> throw CommandHandlerException("Unknown command type")
    }

    private fun onAddReportCommand(command: AddReportCommand) {
        log.debug("Command handler [cmd={}]")
        store.add(Event(
                uuid = UUID.randomUUID().toString(),
                aggregateUuid = UUID.randomUUID().toString(),
                version = 1,
                timestamp = Date().time,
                payloadType = ReportAddedEvent.type,
                payload = Gson().toJson(ReportAddedEvent(
                        report = command.report
                ))
        ))
    }

}