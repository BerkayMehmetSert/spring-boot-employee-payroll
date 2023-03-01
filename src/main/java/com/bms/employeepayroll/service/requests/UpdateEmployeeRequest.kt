package com.bms.employeepayroll.service.requests

import java.time.LocalDate

data class UpdateEmployeeRequest(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate,
    val address: String,
    val email: String,
    val jobTitleId: String,
    val departmentId: String,
    val genderId: String,
    val cityId: String,
)
