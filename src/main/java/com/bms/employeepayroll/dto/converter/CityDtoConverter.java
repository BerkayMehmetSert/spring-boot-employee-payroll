package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.CityDto;
import com.bms.employeepayroll.model.City;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CityDtoConverter {
    private final CityEmployeeDtoConverter employeeDtoConverter;

    public CityDtoConverter(CityEmployeeDtoConverter employeeDtoConverter) {
        this.employeeDtoConverter = employeeDtoConverter;
    }

    public CityDto convert(City from) {
        return new CityDto(
                from.getId(),
                from.getName(),
                Objects.requireNonNull(from.getCountry()).getId(),
                from.getEmployees() != null ? employeeDtoConverter.convert(from.getEmployees()) : null
        );
    }

    public Set<CityDto> convert(Set<City> from) {
        return from.stream().map(this::convert).collect(Collectors.toSet());
    }
}
