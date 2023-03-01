package com.bms.employeepayroll.service.requests

data class CreateAdjustmentRequest(
    val name: String,
    val percentage: Double,
    val isWorkingHoursAdjustment: Boolean,
    val isOtherAdjustment: Boolean
)
