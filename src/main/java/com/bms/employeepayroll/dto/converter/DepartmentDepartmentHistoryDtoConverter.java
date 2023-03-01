package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.DepartmentDepartmentHistoryDto;
import com.bms.employeepayroll.model.DepartmentHistory;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DepartmentDepartmentHistoryDtoConverter {
    public DepartmentDepartmentHistoryDto convert(DepartmentHistory from) {
        return new DepartmentDepartmentHistoryDto(
                from.getId(),
                from.getStartDate(),
                Objects.requireNonNull(from.getEndDate()),
                Objects.requireNonNull(from.getEmployee()).getId()
        );
    }

    public Set<DepartmentDepartmentHistoryDto> convert(Set<DepartmentHistory> from) {
        return from.stream().map(this::convert).collect(Collectors.toSet());
    }
}
