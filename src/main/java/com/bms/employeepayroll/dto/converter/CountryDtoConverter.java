package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.CountryDto;
import com.bms.employeepayroll.model.Country;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CountryDtoConverter {
    private final CountryCityDtoConverter cityDtoConverter;

    public CountryDtoConverter(CountryCityDtoConverter cityDtoConverter) {
        this.cityDtoConverter = cityDtoConverter;
    }

    public CountryDto convert(Country from) {
        return new CountryDto(
                from.getId(),
                from.getName(),
                from.getCities() != null ? cityDtoConverter.convert(from.getCities()) : null
        );
    }

    public Set<CountryDto> convert(Set<Country> from) {
        return from.stream().map(this::convert).collect(Collectors.toSet());
    }
}
