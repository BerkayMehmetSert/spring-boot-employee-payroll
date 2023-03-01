package com.bms.employeepayroll.dto;

import java.time.LocalDate;
import java.util.Set;

public record SalaryPaymentDto(
        String id,
        Double grossSalary,
        Double netSalary,
        LocalDate salaryPeriod,
        String employeeId,
        Set<SalaryPaymentWorkingAdjustmentDto> workingAdjustments
) {
}
