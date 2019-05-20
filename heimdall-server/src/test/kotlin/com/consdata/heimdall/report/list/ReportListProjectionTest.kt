package com.consdata.heimdall.report.list

import com.google.gson.Gson
import com.consdata.heimdall.events.Event
import com.consdata.heimdall.report.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import java.util.*

@DataJpaTest
@Import(ReportListProjection::class)
internal class ReportListProjectionTest @Autowired constructor(
        private val projection: ReportListProjection,
        private val repository: ReportListRepository) {

    @Test
    fun `Should update report list projection`() {
        val uuid = UUID.randomUUID().toString()
        val timestamp = 123L

        // given
        val report = ReportAddedEvent(ArtifactReport(
                name = ArtifactName("test-project", "@glipecki"),
                version = ArtifactVersion(1, 0, 10),
                date = GenerationDate(timestamp),
                modules = listOf(),
                type = ArtifactType.Npm
        ))
        val event = Event(
                uuid = uuid,
                version = 1,
                order = 1,
                timestamp = timestamp,
                aggregateUuid = uuid,
                payloadType = ReportAddedEvent.type,
                payload = Gson().toJson(report)
        )

        // when
        projection.afterReportAddedEvent(event)

        // then
        val list = repository.findAll()
        assertThat(list).hasSize(1)
        assertThat(list[0].reportUuid).isEqualTo(uuid)
        assertThat(list[0].artifact).isEqualTo("npm:@glipecki/test-project@1.0.10")
        assertThat(list[0].timestamp).isEqualTo(timestamp)
    }

}