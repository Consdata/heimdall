package com.consdata.heimdall.report.add

import com.google.gson.Gson
import com.consdata.heimdall.report.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AddReportCommandParserTest {

    private val parser = AddReportDtoToArtifactReportParser()

    @Test
    fun `Should parse DTO as Command`() {
        // given
        val dto = Gson().fromJson(reportJsonDto, AddReportDto::class.java)

        // when
        val result = parser.of(dto)

        // then
        assertThat(result).isEqualTo(artifactReport)
    }

    private val timestamp = 10001

    private val reportJsonDto = """
                        {
                          "project": {
                            "name": "@glipecki/heimdall-scanner",
                            "version": "1.0"
                          },
                          "type": "Npm",
                          "timestamp": "$timestamp",
                          "git": {
                            "branch": "master",
                            "sha": "bca256"
                          },
                          "modules": [
                            {
                              "type": "Npm",
                              "dependencies": [
                                {
                                  "name": "file-system",
                                  "version": "^2.2.2",
                                  "resolution": "2.2.2",
                                  "dependencies": [
                                    {
                                      "name": "file-match",
                                      "version": "^1.0.1",
                                      "resolution": "1.0.2"
                                    }
                                  ]
                                }
                              ]
                            }
                          ]
                        }
                """

    private val artifactReport = ArtifactReport(
            name = ArtifactName(
                    artifact = "heimdall-scanner",
                    group = "@glipecki"
            ),
            type = ArtifactType.Npm,
            version = ArtifactVersion(1, 0, 0),
            date = GenerationDate(
                    timestamp = timestamp
            ),
            git = GitCommit(
                    branch = GitBranch("master"),
                    sha = "bca256"
            ),
            modules = listOf(
                    ArtifactModule(
                            name = ArtifactName(
                                    artifact = "heimdall-scanner",
                                    group = "@glipecki"
                            ),
                            type = ArtifactType.Npm,
                            dependencies = listOf(
                                    ArtifactDependency(
                                            name = ArtifactName(
                                                    artifact = "file-system"
                                            ),
                                            version = DependencyVersion(
                                                    requested = ArtifactVersionRange("^2.2.2"),
                                                    resolved = ArtifactVersion(2, 2, 2)
                                            ),
                                            dependencies = listOf(
                                                    ArtifactDependency(
                                                            name = ArtifactName(
                                                                    artifact = "file-match"
                                                            ),
                                                            version = DependencyVersion(
                                                                    requested = ArtifactVersionRange("^1.0.1"),
                                                                    resolved = ArtifactVersion(1, 0, 2)
                                                            ),
                                                            dependencies = listOf()
                                                    )
                                            )
                                    )
                            )
                    )
            )
    )

}
