package com.bms.employeepayroll.dto;

import java.util.Set;

public record JobTitleDto(
        String id,
        String title,
        Set<JobTitleEmployeeDto> employees,
        Set<JobTitleJobHistoryDto> jobHistories
) {
}
