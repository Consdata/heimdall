package com.consdata.heimdall.projections

interface MultiNodeProjection {

    fun projectionName(): String = this.javaClass.name.substringBefore("$")

}