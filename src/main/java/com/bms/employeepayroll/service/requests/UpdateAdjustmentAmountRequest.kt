package com.bms.employeepayroll.service.requests

data class UpdateAdjustmentAmountRequest(
    val amount: Double,
    val percentage: Double,
    val adjustmentId: String,
    val salaryPaymentId: String
)
