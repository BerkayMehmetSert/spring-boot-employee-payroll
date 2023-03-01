package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.EmployeeDepartmentHistoryDto;
import com.bms.employeepayroll.model.DepartmentHistory;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EmployeeDepartmentHistoryDtoConverter {
    public EmployeeDepartmentHistoryDto convert(DepartmentHistory from) {
        return new EmployeeDepartmentHistoryDto(
                from.getId(),
                from.getStartDate(),
                Objects.requireNonNull(from.getEndDate()),
                Objects.requireNonNull(from.getDepartment()).getId()
        );
    }

    public Set<EmployeeDepartmentHistoryDto> convert(Set<DepartmentHistory> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
