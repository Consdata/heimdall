package com.consdata.heimdall.monitor.tracking

import com.consdata.heimdall.monitor.ArtifactScope
import com.consdata.heimdall.monitor.project.ProjectEntity
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TrackedDependencyEntityTest {

    @Test
    fun shouldCompareTwoVersionsByMajor() {
        val newer = version(2, 0, 0)
        Assertions.assertThat(newer.compare(1, 3, 3)).isEqualTo(1)
    }

    @Test
    fun shouldCompareTwoVersionsByMinor() {
        val newer = version(0, 2, 0)
        Assertions.assertThat(newer.compare(0, 1, 3)).isEqualTo(1)
    }

    @Test
    fun shouldCompareTwoVersionsByPatch() {
        val newer = version(0, 0, 2)
        Assertions.assertThat(newer.compare(0, 0, 1)).isEqualTo(1)
    }

    private fun version(major: Long, minor: Long, patch: Long): TrackedDependencyEntity {
        return TrackedDependencyEntity(
                scope = ArtifactScope.Maven,
                groupId = "",
                artifact = "",
                latestMajor = major,
                latestMinor = minor,
                latestPatch = patch
        )
    }

}