package com.bms.employeepayroll.dto;

import java.util.Set;

public record CityDto(
        String id,
        String name,
        String countryId,
        Set<CityEmployeeDto> employees
) {
}
