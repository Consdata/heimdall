package com.consdata.heimdall.command

interface CommandBus {

    fun dispatch(cmd: Command)

}
