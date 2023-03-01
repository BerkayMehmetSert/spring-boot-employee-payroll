package com.bms.employeepayroll.dto;

import java.time.LocalDate;

public record DepartmentDepartmentHistoryDto(
        String id,
        LocalDate startDate,
        LocalDate endDate,
        String employeeId
) {
}
