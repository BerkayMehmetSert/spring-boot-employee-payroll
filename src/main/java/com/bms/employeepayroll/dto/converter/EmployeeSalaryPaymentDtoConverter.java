package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.EmployeeSalaryPaymentDto;
import com.bms.employeepayroll.model.SalaryPayment;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EmployeeSalaryPaymentDtoConverter {
    public EmployeeSalaryPaymentDto convert(SalaryPayment from) {
        return new EmployeeSalaryPaymentDto(
                from.getId(),
                from.getGrossSalary(),
                from.getNetSalary(),
                from.getSalaryPeriod()
        );
    }

    public Set<EmployeeSalaryPaymentDto> convert(Set<SalaryPayment> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
