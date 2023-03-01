package com.bms.employeepayroll.dto;

public record SalaryPaymentWorkingAdjustmentDto(
        String id,
        Double adjustmentAmount,
        Double adjustmentPercentage,
        String workingHoursId,
        String adjustmentId
) {
}
