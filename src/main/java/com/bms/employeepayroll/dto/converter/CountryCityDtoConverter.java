package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.CountryCityDto;
import com.bms.employeepayroll.model.City;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CountryCityDtoConverter {
    public CountryCityDto convert(City from) {
        return new CountryCityDto(
                from.getId(),
                from.getName()
        );
    }

    public Set<CountryCityDto> convert(Set<City> from) {
        return from.stream().map(this::convert).collect(Collectors.toSet());
    }
}
