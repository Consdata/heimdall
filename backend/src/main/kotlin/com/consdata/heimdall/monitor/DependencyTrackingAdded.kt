package com.consdata.heimdall.monitor

data class DependencyTrackingAdded(
        val id: String,
        val group: String? = null,
        val artifact: String,
        val scope: ArtifactScope
)
