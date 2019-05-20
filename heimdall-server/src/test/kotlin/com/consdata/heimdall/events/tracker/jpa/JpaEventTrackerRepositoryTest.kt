package com.consdata.heimdall.events.tracker.jpa

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class JpaEventTrackerRepositoryTest @Autowired constructor(
        val entityManager: TestEntityManager,
        val repository: JpaEventTrackerRepository) {

    @Test
    fun `Should fetch offset for previously tracked processor`() {
        val key = "key"
        val offset = 17L

        // given
        entityManager.persist(JpaEventTrackerEntity(key, offset))
        entityManager.flush()

        // when
        val found = repository.findByKey(key)

        // then
        assertThat(found?.lastOffset).isEqualTo(offset)
    }


}