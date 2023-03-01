package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.EmploymentTermDto;
import com.bms.employeepayroll.model.EmploymentTerm;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EmploymentTermDtoConverter {
    public EmploymentTermDto convert(EmploymentTerm from) {
        return new EmploymentTermDto(
                from.getId(),
                from.getAgreedSalary(),
                from.getStartDate(),
                Objects.nonNull(from.getEndDate()) ? from.getEndDate() : null,
                Objects.requireNonNull(from.getEmployee()).getId()
        );
    }

    public Set<EmploymentTermDto> convert(Set<EmploymentTerm> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
