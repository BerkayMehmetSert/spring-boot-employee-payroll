package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.DepartmentHistoryDto;
import com.bms.employeepayroll.model.DepartmentHistory;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DepartmentHistoryDtoConverter {
    public DepartmentHistoryDto convert(DepartmentHistory from) {
        return new DepartmentHistoryDto(
                from.getId(),
                from.getStartDate(),
                Objects.requireNonNull(from.getEndDate()),
                Objects.requireNonNull(from.getEmployee()).getId(),
                Objects.requireNonNull(from.getDepartment()).getId()
        );
    }

    public Set<DepartmentHistoryDto> convert(Set<DepartmentHistory> from) {
        return from.stream().map(this::convert).collect(Collectors.toSet());
    }
}
