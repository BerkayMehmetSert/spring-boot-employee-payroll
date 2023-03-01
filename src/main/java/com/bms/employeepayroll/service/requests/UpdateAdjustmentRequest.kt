package com.bms.employeepayroll.service.requests

data class UpdateAdjustmentRequest(
    val name: String,
    val percentage: Double,
    val isWorkingHoursAdjustment: Boolean,
    val isOtherAdjustment: Boolean
)
