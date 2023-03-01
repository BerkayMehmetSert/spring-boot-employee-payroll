package com.bms.employeepayroll.service.requests

import java.time.LocalDate

data class UpdateSalaryPaymentRequest(
    val grossSalary: Double,
    val netSalary: Double,
    val salaryPeriod: LocalDate,
    val employeeId: String
)
