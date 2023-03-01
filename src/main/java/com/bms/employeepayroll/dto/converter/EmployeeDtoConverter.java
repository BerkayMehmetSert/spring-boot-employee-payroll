package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.EmployeeDto;
import com.bms.employeepayroll.model.Employee;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EmployeeDtoConverter {
    private final EmployeeWorkingHoursDtoConverter employeeWorkingHoursDtoConverter;
    private final EmployeeSalaryPaymentDtoConverter employeeSalaryPaymentDtoConverter;
    private final EmployeeEmploymentTermDtoConverter employeeEmploymentTermDtoConverter;
    private final EmployeeJobHistoryDtoConverter employeeJobHistoryDtoConverter;
    private final EmployeeDepartmentHistoryDtoConverter employeeDepartmentHistoryDtoConverter;

    public EmployeeDtoConverter(EmployeeWorkingHoursDtoConverter employeeWorkingHoursDtoConverter,
                                EmployeeSalaryPaymentDtoConverter employeeSalaryPaymentDtoConverter,
                                EmployeeEmploymentTermDtoConverter employeeEmploymentTermDtoConverter,
                                EmployeeJobHistoryDtoConverter employeeJobHistoryDtoConverter,
                                EmployeeDepartmentHistoryDtoConverter employeeDepartmentHistoryDtoConverter) {
        this.employeeWorkingHoursDtoConverter = employeeWorkingHoursDtoConverter;
        this.employeeSalaryPaymentDtoConverter = employeeSalaryPaymentDtoConverter;
        this.employeeEmploymentTermDtoConverter = employeeEmploymentTermDtoConverter;
        this.employeeJobHistoryDtoConverter = employeeJobHistoryDtoConverter;
        this.employeeDepartmentHistoryDtoConverter = employeeDepartmentHistoryDtoConverter;
    }

    public EmployeeDto convert(Employee from) {
        return new EmployeeDto(
                from.getId(),
                from.getFirstName(),
                from.getLastName(),
                from.getDateOfBirth(),
                from.getAddress(),
                from.getEmail(),
                from.getEmploymentStart(),
                Objects.requireNonNull(from.getJobTitle()).getId(),
                Objects.requireNonNull(from.getDepartment()).getId(),
                Objects.requireNonNull(from.getGender()).getId(),
                Objects.requireNonNull(from.getCity()).getId(),
                from.getWorkingHours() != null ? employeeWorkingHoursDtoConverter.convert(from.getWorkingHours()) : null,
                from.getSalaryPayments() != null ? employeeSalaryPaymentDtoConverter.convert(from.getSalaryPayments()) : null,
                from.getEmploymentTerms() != null ? employeeEmploymentTermDtoConverter.convert(from.getEmploymentTerms()) : null,
                from.getJobHistories() != null ? employeeJobHistoryDtoConverter.convert(from.getJobHistories()) : null,
                from.getDepartmentHistories() != null ? employeeDepartmentHistoryDtoConverter.convert(from.getDepartmentHistories()) : null
        );
    }

    public Set<EmployeeDto> convert(Set<Employee> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
