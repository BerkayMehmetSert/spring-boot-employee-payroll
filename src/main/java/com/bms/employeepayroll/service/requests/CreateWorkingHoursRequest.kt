package com.bms.employeepayroll.service.requests

import java.time.LocalDateTime

data class CreateWorkingHoursRequest(
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val employeeId: String
)
