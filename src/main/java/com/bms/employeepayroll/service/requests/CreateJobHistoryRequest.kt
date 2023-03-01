package com.bms.employeepayroll.service.requests

import java.time.LocalDate

data class CreateJobHistoryRequest(
    val startDate: LocalDate,
    val endDate: LocalDate?,
    val employeeId: String,
    val jobTitleId: String
)
