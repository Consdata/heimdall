package com.consdata.heimdall.command

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