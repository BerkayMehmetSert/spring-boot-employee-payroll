package com.bms.employeepayroll.dto;

import java.util.Set;

public record GenderDto(
        String id,
        String name,
        Set<GenderEmployeeDto> employees
) {
}
