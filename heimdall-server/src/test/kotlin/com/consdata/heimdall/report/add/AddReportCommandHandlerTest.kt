package com.consdata.heimdall.report.add

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import com.consdata.heimdall.events.Event
import com.consdata.heimdall.events.store.EventStore
import com.consdata.heimdall.report.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class AddReportCommandHandlerTest {

    @MockK
    lateinit var store: EventStore

    @InjectMockKs
    lateinit var commandHandler: AddReportCommandHandler

    @Test
    fun `Should store ReportAdded event for AddReport command`() {
        val slot = slot<Event>()

        // given
        every { store.add(capture(slot)) } returnsArgument 0

        // when
        commandHandler.on(AddReportCommand(report = aReport()))

        // then
        assertThat(slot.captured).isNotNull
    }

    private fun aReport() = ArtifactReport(
            name = ArtifactName("", ""),
            version = ArtifactVersion(0, 0, 0),
            date = GenerationDate(0),
            git = GitCommit(GitBranch(""), ""),
            modules = listOf(),
            type = ArtifactType.Npm
    )

}