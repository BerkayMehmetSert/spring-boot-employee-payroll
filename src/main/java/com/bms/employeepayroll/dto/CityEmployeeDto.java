package com.bms.employeepayroll.dto;

import java.time.LocalDate;

public record CityEmployeeDto(
        String id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String address,
        String email,
        LocalDate employmentStart,
        String jobTitleId,
        String departmentId,
        String genderId
) {
}
