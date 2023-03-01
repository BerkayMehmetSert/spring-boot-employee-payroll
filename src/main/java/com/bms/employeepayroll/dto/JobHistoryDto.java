package com.bms.employeepayroll.dto;

import java.time.LocalDate;

public record JobHistoryDto(
        String id,
        LocalDate startDate,
        LocalDate endDate,
        String employeeId,
        String jobTitleId
) {
}
