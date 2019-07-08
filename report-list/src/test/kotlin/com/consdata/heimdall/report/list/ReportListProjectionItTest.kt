package com.consdata.heimdall.report.list

import com.consdata.heimdall.report.*
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.containsInAnyOrder
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.JsonPathResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(properties = ["app.instance.uuid=test"])
internal class ReportListProjectionItTest @Autowired constructor(
        private val mvc: MockMvc,
        private val projection: ReportListProjection
) {

    @Test
    fun shouldListAddedReport() {
        val reportAdded = ReportAddedEvent(
                id = "report-uuid",
                timestamp = 999,
                report = ArtifactReport(
                        name = ArtifactName("artifact", "group"),
                        version = ArtifactVersion(1, 2, 3),
                        date = GenerationDate(1),
                        dependencies = mapOf(),
                        type = ArtifactType.Npm
                )
        )

        projection.on(reportAdded)

        mvc
                .perform(
                        MockMvcRequestBuilders.get("/report")
                )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[*].timestamp", contains(999)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[*].reportUuid", contains("report-uuid")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[*].artifact", contains("npm:group/artifact@1.2.3")))
    }

}
