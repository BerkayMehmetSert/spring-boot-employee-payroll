package com.bms.employeepayroll.dto;

import java.time.LocalDate;

public record EmployeeDepartmentHistoryDto(
        String id,
        LocalDate startDate,
        LocalDate endDate,
        String departmentId
) {
}
