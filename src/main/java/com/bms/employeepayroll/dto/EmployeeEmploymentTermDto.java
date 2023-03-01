package com.bms.employeepayroll.dto;

import java.time.LocalDate;

public record EmployeeEmploymentTermDto(
        String id,
        Double agreedSalary,
        LocalDate startDate,
        LocalDate endDate
) {
}
