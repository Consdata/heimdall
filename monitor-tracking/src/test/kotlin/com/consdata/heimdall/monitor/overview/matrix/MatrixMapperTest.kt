package com.consdata.heimdall.monitor.overview.matrix

import com.consdata.heimdall.monitor.ArtifactScope
import com.consdata.heimdall.monitor.overview.DependencyOverviewEntity
import com.consdata.heimdall.monitor.overview.TrackingStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MatrixMapperTest {
    private var mapper: MatrixMapper = MatrixMapper()

    @Test
    fun shouldMap() {
        // given
        val entity1 = getEntity(ANGULAR, PROJECT_A, 1)
        val entity2 = getEntity(ANGULAR, PROJECT_B, 2)
        val entity3 = getEntity(SPRING, PROJECT_C, 3)
        val entity4 = getEntity(TYPE_SCRIPT, PROJECT_C, 4)
        val entity5 = getEntity(JAVA, PROJECT_B, 5)
        val dependencyOverviewEntityList = listOf(entity1, entity2, entity3, entity4, entity5)

        // when
        val map = mapper.map(dependencyOverviewEntityList)

        // then
        assertThat(map.columns).hasSize(4)
        assertThat(map.columns).extracting("dependencyArtifact").containsExactly(ANGULAR, SPRING, TYPE_SCRIPT, JAVA)

        assertThat(map.rows).hasSize(3)
        assertThat(map.rows).extracting("projectArtifact").containsExactly(PROJECT_A, PROJECT_B, PROJECT_C)

        // Angular
        assertThat(map.matrix[0][0]!!.versionMajor).isEqualTo(1)
        assertThat(map.matrix[0][1]!!.versionMajor).isEqualTo(2)
        assertThat(map.matrix[0][2]).isNull()

        // Spring
        assertThat(map.matrix[1][0]).isNull()
        assertThat(map.matrix[1][1]).isNull()
        assertThat(map.matrix[1][2]!!.versionMajor).isEqualTo(3)

        // TypeScript
        assertThat(map.matrix[2][0]).isNull()
        assertThat(map.matrix[2][1]).isNull()
        assertThat(map.matrix[2][2]!!.versionMajor).isEqualTo(4)

        // Java
        assertThat(map.matrix[3][0]).isNull()
        assertThat(map.matrix[3][1]!!.versionMajor).isEqualTo(5)
        assertThat(map.matrix[3][2]).isNull()
    }

    private fun getEntity(dependency: String, project: String, version: Long): DependencyOverviewEntity {
        return DependencyOverviewEntity(
                null,
                0,
                0,
                ArtifactScope.Maven,
                null,
                dependency,
                0,
                0,
                0,
                null,
                project,
                version,
                version,
                version,
                version,
                version,
                version,
                TrackingStatus.Current)
    }

    companion object {
        private const val ANGULAR: String = "Angular"
        private const val SPRING: String = "Spring"
        private const val TYPE_SCRIPT: String = "TypeScript"
        private const val JAVA: String = "Java"
        private const val PROJECT_A: String = "project-A"
        private const val PROJECT_B: String = "project-B"
        private const val PROJECT_C: String = "project-C"
    }
}
