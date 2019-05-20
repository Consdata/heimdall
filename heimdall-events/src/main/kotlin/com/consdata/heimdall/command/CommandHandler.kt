package com.consdata.heimdall.command

interface CommandHandler {

    fun on(command: Command)
    fun supported(): List<Class<out Command>>

}
