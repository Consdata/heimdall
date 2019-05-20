package com.consdata.heimdall.info

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/info")
internal class InfoController {

    @GetMapping("")
    fun info() = "Heimdall"

}