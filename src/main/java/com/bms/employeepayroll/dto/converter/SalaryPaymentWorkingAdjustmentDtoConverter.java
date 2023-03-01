package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.SalaryPaymentWorkingAdjustmentDto;
import com.bms.employeepayroll.model.WorkingAdjustment;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SalaryPaymentWorkingAdjustmentDtoConverter {
    public SalaryPaymentWorkingAdjustmentDto convert(WorkingAdjustment from) {
        return new SalaryPaymentWorkingAdjustmentDto(
                from.getId(),
                from.getAdjustmentAmount(),
                from.getAdjustmentPercentage(),
                Objects.requireNonNull(from.getWorkingHours()).getId(),
                Objects.requireNonNull(from.getAdjustment()).getId()
        );
    }

    public Set<SalaryPaymentWorkingAdjustmentDto> convert(Set<WorkingAdjustment> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
