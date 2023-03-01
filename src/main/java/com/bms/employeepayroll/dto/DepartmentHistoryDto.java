package com.bms.employeepayroll.dto;

import java.time.LocalDate;

public record DepartmentHistoryDto(
        String id,
        LocalDate startDate,
        LocalDate endDate,
        String employeeId,
        String departmentId
) {
}
