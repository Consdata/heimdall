package com.consdata.heimdall.command.simple

import com.consdata.heimdall.command.Command
import com.consdata.heimdall.command.CommandBus
import com.consdata.heimdall.command.CommandHandler
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class SimpleCommandBus(private val handlers: List<CommandHandler>) : CommandBus {

    private val mapping: MutableMap<Class<out Command>, MutableList<CommandHandler>> = mutableMapOf()

    @PostConstruct
    fun mapHandlers() {
        handlers.forEach {
            it.supported().forEach { type -> mapping.getOrPut(type, { mutableListOf() }).add(it) }
        }
    }

    override fun dispatch(cmd: Command) {
        mapping[cmd.javaClass]?.forEach { it.on(cmd) }
    }

}