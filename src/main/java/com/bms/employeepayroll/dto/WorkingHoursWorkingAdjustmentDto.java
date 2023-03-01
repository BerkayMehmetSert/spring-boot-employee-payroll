package com.bms.employeepayroll.dto;

public record WorkingHoursWorkingAdjustmentDto(
        String id,
        Double adjustmentAmount,
        Double adjustmentPercentage,
        String adjustmentId,
        String salaryPaymentId
) {
}
