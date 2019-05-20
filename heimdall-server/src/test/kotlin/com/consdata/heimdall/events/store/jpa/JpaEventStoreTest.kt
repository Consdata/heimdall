package com.consdata.heimdall.events.store.jpa

import com.consdata.heimdall.events.Event
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.context.annotation.Import

@DataJpaTest
@Import(JpaEventStore::class)
internal class JpaEventStoreTest @Autowired constructor(
        val entityManager: TestEntityManager,
        val store: JpaEventStore
) {

    @Test
    fun `Should store event`() {
        // given
        val event = anEvent(uuid)

        // when
        val stored = store.add(event)

        // then
        val entity = entityManager.find(JpaEventEntity::class.java, stored.order)
        assertThat(entity).isNotNull
    }

    @Test
    fun `Should fetch single event`() {
        // given
        val event1 = entityManager.persist(anEventEntity("11"))
        entityManager.persist(anEventEntity("12"))

        // when
        val events = store.eventsWindow(event1.sequence - 1, 1)

        // then
        assertThat(events).extracting("uuid").containsExactly("11")
    }

    @Test
    fun `Should fetch more events`() {
        // given
        val event1 = entityManager.persist(anEventEntity("21"))
        entityManager.persist(anEventEntity("22"))
        entityManager.persist(anEventEntity("23"))

        // when
        val events = store.eventsWindow(event1.sequence - 1, 3)

        // then
        assertThat(events).extracting("uuid").containsExactly("21", "22", "23")
    }

    @Test
    fun `Should fetch event with offset`() {
        // given
        entityManager.persist(anEventEntity("31"))
        val event2 = entityManager.persist(anEventEntity("32"))
        entityManager.persist(anEventEntity("33"))

        // when
        val events = store.eventsWindow(event2.sequence - 1, 1)

        // then
        assertThat(events).extracting("uuid").containsExactly("32")
    }

    private val uuid = "1"

    private fun anEvent(uuid: String) = Event(
            uuid = uuid,
            aggregateUuid = "1",
            version = 1,
            timestamp = 1,
            payload = "payload",
            payloadType = String::class.java.canonicalName
    )

    private fun anEventEntity(uuid: String) = JpaEventEntity(
            uuid = uuid,
            aggregateUuid = "1",
            version = 1,
            timestamp = 1,
            payload = "payload",
            payloadType = String::class.java.canonicalName
    )

}