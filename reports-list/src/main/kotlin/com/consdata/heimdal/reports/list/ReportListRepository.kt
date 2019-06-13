package com.consdata.heimdal.reports.list

import org.springframework.data.jpa.repository.JpaRepository

interface ReportListRepository : JpaRepository<ReportListEntity, Long>
