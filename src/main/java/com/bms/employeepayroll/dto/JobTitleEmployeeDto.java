package com.bms.employeepayroll.dto;

import java.time.LocalDate;

public record JobTitleEmployeeDto(
        String id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String address,
        String email,
        LocalDate employmentStart,
        String departmentId,
        String genderId,
        String cityId
) {
}
