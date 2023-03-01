package com.bms.employeepayroll.dto;

import java.time.LocalDate;

public record JobTitleJobHistoryDto(
        String id,
        LocalDate startDate,
        LocalDate endDate,
        String employeeId
) {
}
