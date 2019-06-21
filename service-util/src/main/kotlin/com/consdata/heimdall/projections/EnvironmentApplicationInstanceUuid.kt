package com.consdata.heimdall.projections

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

open class EnvironmentApplicationInstanceUuid : ApplicationInstanceUuid {

    @Value("\${app.instance.uuid}")
    lateinit var applicationInstanceUuid: String;

    override fun uuid() = this.applicationInstanceUuid

}