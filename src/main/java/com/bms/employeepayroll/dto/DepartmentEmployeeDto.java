package com.bms.employeepayroll.dto;

import java.time.LocalDate;

public record DepartmentEmployeeDto(
        String id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String address,
        String email,
        LocalDate employmentStart,
        String jobTitleId,
        String genderId
) {
}
