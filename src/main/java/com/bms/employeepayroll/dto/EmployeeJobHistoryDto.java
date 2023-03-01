package com.bms.employeepayroll.dto;

import java.time.LocalDate;

public record EmployeeJobHistoryDto(
        String id,
        LocalDate startDate,
        LocalDate endDate,
        String jobTitleId
) {
}
