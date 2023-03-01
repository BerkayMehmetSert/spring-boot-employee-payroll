package com.bms.employeepayroll.service.requests

import java.time.LocalDate

data class CreateDepartmentHistoryRequest(
    val startDate: LocalDate,
    val endDate: LocalDate?,
    val employeeId: String,
    val departmentId: String
)
