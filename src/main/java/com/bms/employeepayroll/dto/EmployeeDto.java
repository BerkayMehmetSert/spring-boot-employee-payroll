package com.bms.employeepayroll.dto;

import java.time.LocalDate;
import java.util.Set;

public record EmployeeDto(
        String id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String address,
        String email,
        LocalDate employmentStart,
        String jobTitleId,
        String departmentId,
        String genderId,
        String cityId,
        Set<EmployeeWorkingHoursDto> workingHours,
        Set<EmployeeSalaryPaymentDto> salaryPayments,
        Set<EmployeeEmploymentTermDto> employmentTerms,
        Set<EmployeeJobHistoryDto> jobHistories,
        Set<EmployeeDepartmentHistoryDto> departmentHistories
) {
}
