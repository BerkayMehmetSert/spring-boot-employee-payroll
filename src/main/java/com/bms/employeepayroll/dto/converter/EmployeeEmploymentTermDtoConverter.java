package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.EmployeeEmploymentTermDto;
import com.bms.employeepayroll.model.EmploymentTerm;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EmployeeEmploymentTermDtoConverter {
    public EmployeeEmploymentTermDto convert(EmploymentTerm from) {
        return new EmployeeEmploymentTermDto(
                from.getId(),
                from.getAgreedSalary(),
                from.getStartDate(),
                Objects.nonNull(from.getEndDate()) ? from.getEndDate() : null
        );
    }

    public Set<EmployeeEmploymentTermDto> convert(Set<EmploymentTerm> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
