package com.bms.employeepayroll.dto;

import java.time.LocalDate;

public record EmploymentTermDto(
        String id,
        Double agreedSalary,
        LocalDate startDate,
        LocalDate endDate,
        String employeeId
) {
}
