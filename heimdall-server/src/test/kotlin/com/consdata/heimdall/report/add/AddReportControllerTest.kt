package com.consdata.heimdall.report.add

import com.google.gson.Gson
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import com.consdata.heimdall.command.CommandBus
import com.consdata.heimdall.report.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(AddReportController::class)
internal class AddReportControllerTest @Autowired constructor(private val mvc: MockMvc) {

    @MockkBean(relaxed = true)
    private lateinit var commandBus: CommandBus

    @MockkBean
    private lateinit var artifactReportParser: AddReportDtoToArtifactReportParser

    @Test
    fun `Should dispatch AddReportCommand for valid report added via REST call`() {
        val report = aArtifactReport()
        val command = AddReportCommand(report)
        val dto = aDto()

        // given
        every { artifactReportParser.of(dto) } returns report

        // when
        mvc.perform(postRequest(dto)).andExpect(status().isOk)

        // then
        verify { commandBus.dispatch(command) }
    }

    private fun postRequest(dto: AddReportDto): MockHttpServletRequestBuilder {
        return post("/report")
                .content(Gson().toJson(dto))
                .contentType(MediaType.APPLICATION_JSON)
    }

    private fun aArtifactReport(): ArtifactReport {
        return ArtifactReport(
                name = ArtifactName("", ""),
                version = ArtifactVersion(0, 0, 0),
                date = GenerationDate(0),
                git = GitCommit(GitBranch(""), ""),
                modules = listOf(),
                type = ArtifactType.Npm
        )
    }

    private fun aDto() = AddReportDto(AddReportModuleTypeDto.Npm, "", AddReportProjectDto("", ""), AddReportGitDto("", ""), listOf())

}
