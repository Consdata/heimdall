package com.consdata.heimdall.report.add

import com.consdata.heimdall.report.ArtifactVersion
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class ArtifactVersionParserTest {

    private val parser = DefaultReportDtoParser()

    @ParameterizedTest
    @MethodSource("versions")
    fun testVersionParser(input: String, expected: ArtifactVersion) {
        assertThat(parser.artifactVersion(input)).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun versions() = listOf(
                Arguments.of("1.9.6", ArtifactVersion(1, 9, 6, 0, "", "1.9.6")),
                Arguments.of("1.54.0-SNAPSHOT", ArtifactVersion(1, 54, 0, 0, "SNAPSHOT", "1.54.0-SNAPSHOT")),
                Arguments.of("3.0.5.RELEASE", ArtifactVersion(3, 0, 5, 0, "RELEASE", "3.0.5.RELEASE")),
                Arguments.of("3.6", ArtifactVersion(3, 6, 0, 0, "", "3.6")),
                Arguments.of("1", ArtifactVersion(1, 0, 0, 0, "", "1")),
                Arguments.of("1_1", ArtifactVersion(1, 0, 0, 0, "1", "1_1")),
                Arguments.of("1-SNAPSHOT", ArtifactVersion(1, 0, 0, 0, "SNAPSHOT", "1-SNAPSHOT")),
                Arguments.of("1.2-SNAPSHOT", ArtifactVersion(1, 2, 0, 0, "SNAPSHOT", "1.2-SNAPSHOT")),
                Arguments.of("1.2.3", ArtifactVersion(1, 2, 3, 0, "", "1.2.3")),
                Arguments.of("1.2", ArtifactVersion(1, 2, 0, 0, "", "1.2")),
                Arguments.of("1.2.3.4", ArtifactVersion(1, 2, 3, 4, "", "1.2.3.4")),
                Arguments.of("1.2.RELEASE", ArtifactVersion(1, 2, 0, 0, "RELEASE", "1.2.RELEASE")),
                Arguments.of("1.2.3.RELEASE", ArtifactVersion(1, 2, 3, 0, "RELEASE", "1.2.3.RELEASE")),
                Arguments.of("1.2.3.4.RELEASE", ArtifactVersion(1, 2, 3, 4, "RELEASE", "1.2.3.4.RELEASE")),
                Arguments.of("1.2.3.SNAPSHOT", ArtifactVersion(1, 2, 3, 0, "SNAPSHOT", "1.2.3.SNAPSHOT")),
                Arguments.of("2.1_1", ArtifactVersion(2, 1, 0, 0, "1", "2.1_1")),
                Arguments.of("1.1.2_1", ArtifactVersion(1, 1, 2, 0, "1", "1.1.2_1")),
                Arguments.of("1.2.3.4_3", ArtifactVersion(1, 2, 3, 4, "3", "1.2.3.4_3")),
                Arguments.of("jdk15", ArtifactVersion(0, 0, 0, 0, "jdk15", "jdk15"))
        )
    }

}
