package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.JobTitleEmployeeDto;
import com.bms.employeepayroll.model.Employee;
import com.bms.employeepayroll.model.JobTitle;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JobTitleEmployeeDtoConverter {
    public JobTitleEmployeeDto convert(Employee from){
        return new JobTitleEmployeeDto(
                from.getId(),
                from.getFirstName(),
                from.getLastName(),
                from.getDateOfBirth(),
                from.getAddress(),
                from.getEmail(),
                from.getEmploymentStart(),
                Objects.requireNonNull(from.getDepartment()).getId(),
                Objects.requireNonNull(from.getGender()).getId(),
                Objects.requireNonNull(from.getCity()).getId()
        );
    }

    public Set<JobTitleEmployeeDto> convert(Set<Employee> from){
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
