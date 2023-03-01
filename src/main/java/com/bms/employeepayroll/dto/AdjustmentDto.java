package com.bms.employeepayroll.dto;

import java.util.Set;

public record AdjustmentDto(
        String id,
        String name,
        Double percentage,
        Boolean isWorkingHoursAdjustment,
        Boolean isOtherAdjustment,
        Set<AdjustmentWorkingAdjustmentDto> workingAdjustments,
        Set<AdjustmentAdjustmentAmountDto> adjustmentAmounts
) {
}
