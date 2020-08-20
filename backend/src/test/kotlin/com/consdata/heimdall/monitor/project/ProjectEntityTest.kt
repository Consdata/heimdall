package com.consdata.heimdall.monitor.project

import com.consdata.heimdall.monitor.ArtifactScope
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

internal class ProjectEntityTest {

    @Test
    fun shouldCompareTwoVersionsByMajor() {
        val older = version(1, 3 ,3)
        val newer = version(2, 0 ,0)
        assertThat(newer.compare(older)).isEqualTo(1)
    }

    @Test
    fun shouldCompareTwoVersionsByMinor() {
        val older = version(0, 1 ,3)
        val newer = version(0, 2 ,0)
        assertThat(newer.compare(older)).isEqualTo(1)
    }

    @Test
    fun shouldCompareTwoVersionsByPatch() {
        val older = version(0, 0 ,1)
        val newer = version(0, 0 ,2)
        assertThat(newer.compare(older)).isEqualTo(1)
    }

    private fun version(major: Long, minor: Long, patch: Long): ProjectEntity {
        return ProjectEntity(
                scope = ArtifactScope.Maven,
                groupId = "",
                artifact = "",
                versionMajor = major,
                versionMinor = minor,
                versionPatch = patch
        )
    }

}