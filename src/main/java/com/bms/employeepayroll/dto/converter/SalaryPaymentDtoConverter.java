package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.SalaryPaymentDto;
import com.bms.employeepayroll.model.SalaryPayment;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SalaryPaymentDtoConverter {
    private final SalaryPaymentWorkingAdjustmentDtoConverter salaryPaymentWorkingAdjustmentDtoConverter;

    public SalaryPaymentDtoConverter(SalaryPaymentWorkingAdjustmentDtoConverter salaryPaymentWorkingAdjustmentDtoConverter) {
        this.salaryPaymentWorkingAdjustmentDtoConverter = salaryPaymentWorkingAdjustmentDtoConverter;
    }

    public SalaryPaymentDto convert(SalaryPayment from) {
        return new SalaryPaymentDto(
                from.getId(),
                from.getGrossSalary(),
                from.getNetSalary(),
                from.getSalaryPeriod(),
                Objects.requireNonNull(from.getEmployee()).getId(),
                from.getWorkingAdjustments() != null ? salaryPaymentWorkingAdjustmentDtoConverter.convert(from.getWorkingAdjustments()) : null
        );
    }

    public Set<SalaryPaymentDto> convert(Set<SalaryPayment> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
