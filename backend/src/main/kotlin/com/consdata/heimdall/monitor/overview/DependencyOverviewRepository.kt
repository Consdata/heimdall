package com.consdata.heimdall.monitor.overview

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query


interface DependencyOverviewRepository : JpaRepository<DependencyOverviewEntity, Long> {

    fun deleteByProjectId(it: Long)

    @Modifying
    @Query("""
        update 
            DependencyOverviewEntity e 
        set 
            e.dependencyLatestMajor = ?1, 
            e.dependencyLatestMinor = ?2, 
            e.dependencyLatestPatch = ?3,
            e.status = ?5
        where 
            e.trackingId = ?4
            and (
                    e.dependencyLatestMajor <> ?1 
                or e.dependencyLatestMinor <> ?2 
                or e.dependencyLatestPatch <> ?3
            )
    """)
    fun setLatestDependency(major: Long, minor: Long, patch: Long, trackingId: Long, status: TrackingStatus = TrackingStatus.Outdated)
}
