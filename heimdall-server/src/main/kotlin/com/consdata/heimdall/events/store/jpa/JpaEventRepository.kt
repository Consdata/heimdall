package com.consdata.heimdall.events.store.jpa

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface JpaEventRepository : JpaRepository<JpaEventEntity, Long> {

    fun findBySequenceGreaterThanOrderBySequenceAsc(sequence: Long, pageable: Pageable): List<JpaEventEntity>

}