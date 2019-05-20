package com.consdata.heimdall.events.tracker.jpa

import org.springframework.data.repository.CrudRepository

interface JpaEventTrackerRepository : CrudRepository<JpaEventTrackerEntity, Long> {

    fun findByKey(key: String): JpaEventTrackerEntity?

}