package com.bms.employeepayroll.service.requests

import java.time.LocalDate

data class UpdateDepartmentHistoryRequest(
    val startDate: LocalDate,
    val endDate: LocalDate?,
    val employeeId: String,
    val departmentId: String
)
