package com.bms.employeepayroll.dto;

public record WorkingAdjustmentDto(
        String id,
        Double adjustmentAmount,
        Double adjustmentPercentage,
        String workingHoursId,
        String adjustmentId,
        String salaryPaymentId
) {
}
