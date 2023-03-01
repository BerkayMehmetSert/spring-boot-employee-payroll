package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.DepartmentDto;
import com.bms.employeepayroll.model.Department;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DepartmentDtoConverter {
    private final DepartmentDepartmentHistoryDtoConverter departmentHistoryDtoConverter;
    private final DepartmentEmployeeDtoConverter employeeDtoConverter;

    public DepartmentDtoConverter(DepartmentDepartmentHistoryDtoConverter departmentHistoryDtoConverter,
                                  DepartmentEmployeeDtoConverter employeeDtoConverter) {
        this.departmentHistoryDtoConverter = departmentHistoryDtoConverter;
        this.employeeDtoConverter = employeeDtoConverter;
    }

    public DepartmentDto convert(Department from) {
        return new DepartmentDto(
                from.getId(),
                from.getName(),
                from.getEmployees() != null ? employeeDtoConverter.convert(from.getEmployees()) : null,
                from.getDepartmentHistories() != null ? departmentHistoryDtoConverter.convert(from.getDepartmentHistories()) : null
        );
    }

    public Set<DepartmentDto> convert(Set<Department> from) {
        return from.stream().map(this::convert).collect(Collectors.toSet());
    }
}
