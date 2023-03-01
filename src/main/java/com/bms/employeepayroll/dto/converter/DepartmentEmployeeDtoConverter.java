package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.DepartmentEmployeeDto;
import com.bms.employeepayroll.model.Employee;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DepartmentEmployeeDtoConverter {
    public DepartmentEmployeeDto convert(Employee from) {
        return new DepartmentEmployeeDto(
                from.getId(),
                from.getFirstName(),
                from.getLastName(),
                from.getDateOfBirth(),
                from.getAddress(),
                from.getEmail(),
                from.getEmploymentStart(),
                Objects.requireNonNull(from.getJobTitle()).getId(),
                Objects.requireNonNull(from.getGender()).getId()
        );
    }

    public Set<DepartmentEmployeeDto> convert(Set<Employee> from) {
        return from.stream().map(this::convert).collect(Collectors.toSet());
    }
}
