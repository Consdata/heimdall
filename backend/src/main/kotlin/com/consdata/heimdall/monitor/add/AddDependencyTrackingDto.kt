package com.consdata.heimdall.monitor.add

internal data class AddDependencyTrackingDto(
        val group: String?,
        val artifact: String,
        val scope: String
)
