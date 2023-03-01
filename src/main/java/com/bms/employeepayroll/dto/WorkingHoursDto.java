package com.bms.employeepayroll.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record WorkingHoursDto(
        String id,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String employeeId,
        Set<WorkingHoursWorkingAdjustmentDto> workingAdjustments
) {
}
