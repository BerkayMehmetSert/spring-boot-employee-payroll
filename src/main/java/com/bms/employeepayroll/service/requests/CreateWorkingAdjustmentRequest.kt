package com.bms.employeepayroll.service.requests

data class CreateWorkingAdjustmentRequest(
    val adjustmentAmount: Double,
    val adjustmentPercentage: Double,
    val workingHoursId: String,
    val adjustmentId: String,
    val salaryPaymentId: String
)
