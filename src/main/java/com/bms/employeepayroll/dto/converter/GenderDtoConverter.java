package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.GenderDto;
import com.bms.employeepayroll.model.Gender;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GenderDtoConverter {
    private final GenderEmployeeDtoConverter genderEmployeeDtoConverter;

    public GenderDtoConverter(GenderEmployeeDtoConverter genderEmployeeDtoConverter) {
        this.genderEmployeeDtoConverter = genderEmployeeDtoConverter;
    }

    public GenderDto convert(Gender from) {
        return new GenderDto(
                from.getId(),
                from.getName(),
                from.getEmployees() != null ? genderEmployeeDtoConverter.convert(from.getEmployees()) : null
        );
    }

    public Set<GenderDto> convert(Set<Gender> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
