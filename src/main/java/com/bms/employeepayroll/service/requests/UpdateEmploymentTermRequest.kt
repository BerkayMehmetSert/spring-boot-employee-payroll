package com.bms.employeepayroll.service.requests

import java.time.LocalDate

data class UpdateEmploymentTermRequest(
    val agreedSalary: Double,
    val startDate: LocalDate,
    val endDate: LocalDate?,
    var employeeId:String
)