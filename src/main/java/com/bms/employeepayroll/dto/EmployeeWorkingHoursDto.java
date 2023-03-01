package com.bms.employeepayroll.dto;

import java.time.LocalDateTime;

public record EmployeeWorkingHoursDto(
        String id,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}
