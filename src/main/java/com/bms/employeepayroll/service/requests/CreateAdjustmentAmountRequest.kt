package com.bms.employeepayroll.service.requests

data class CreateAdjustmentAmountRequest(
    val amount: Double,
    val percentage: Double,
    val adjustmentId: String,
    val salaryPaymentId: String
)
