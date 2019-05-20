package com.consdata.heimdall.dependency.list

import com.consdata.heimdall.events.EventTestSupport
import com.consdata.heimdall.report.ArtifactReportTestSupport
import com.consdata.heimdall.report.add.ReportAddedEvent
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import

@DataJpaTest
@Import(DependencyListProjection::class)
internal class DependencyListProjectionTest @Autowired constructor(
        private val projection: DependencyListProjection,
        private val repository: DependencyListRepository) {

    @Test
    fun shouldCreateDependencyFromReport() {
        // given
        val event = EventTestSupport.anEvent(ReportAddedEvent(ArtifactReportTestSupport.anArtifactReport(listOf(
                ArtifactReportTestSupport.anArtifactDependency("@glipecki", "test-dependency-1", 2, 0, 4)
        ))))

        // when
        projection.afterReportAddedEvent(event)

        // then
        val dependencies = repository.findAll()
        assertThat(dependencies).hasSize(1)
        assertThat(dependencies[0].artifactGroup).isEqualTo("@glipecki")
        assertThat(dependencies[0].artifactName).isEqualTo("test-dependency-1")
        assertThat(dependencies[0].major).isEqualTo(2)
        assertThat(dependencies[0].minor).isEqualTo(0)
        assertThat(dependencies[0].patch).isEqualTo(4)
    }

    @Test
    fun shouldNotCreateDuplicateEntries() {
        // given
        val event = EventTestSupport.anEvent(ReportAddedEvent(ArtifactReportTestSupport.anArtifactReport(listOf(
                ArtifactReportTestSupport.anArtifactDependency("@glipecki", "test-dependency-1", 2, 0, 4),
                ArtifactReportTestSupport.anArtifactDependency("@glipecki", "test-dependency-1", 2, 0, 4)
        ))))

        // when
        projection.afterReportAddedEvent(event)

        // then
        val dependencies = repository.findAll()
        assertThat(dependencies).hasSize(1)
    }


}