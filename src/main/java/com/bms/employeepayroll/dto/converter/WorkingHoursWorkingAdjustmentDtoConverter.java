package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.WorkingHoursWorkingAdjustmentDto;
import com.bms.employeepayroll.model.WorkingAdjustment;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class WorkingHoursWorkingAdjustmentDtoConverter {
    public WorkingHoursWorkingAdjustmentDto convert(WorkingAdjustment from) {
        return new WorkingHoursWorkingAdjustmentDto(
                from.getId(),
                from.getAdjustmentAmount(),
                from.getAdjustmentPercentage(),
                Objects.requireNonNull(from.getAdjustment()).getId(),
                Objects.requireNonNull(from.getSalaryPayment()).getId()
        );
    }

    public Set<WorkingHoursWorkingAdjustmentDto> convert(Set<WorkingAdjustment> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
