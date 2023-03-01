package com.bms.employeepayroll.dto;

import java.time.LocalDate;

public record EmployeeSalaryPaymentDto(
        String id,
        Double grossSalary,
        Double netSalary,
        LocalDate salaryPeriod
) {
}
