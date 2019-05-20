package com.consdata.heimdall.info

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(InfoController::class)
@AutoConfigureRestDocs
class InfoItTest @Autowired constructor(private val mvc: MockMvc) {

    @Test
    fun `Should get app name from info endpoint`() {
        mvc.get("/info") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk }
        }.andDo {
            document("info")
        }
    }

}