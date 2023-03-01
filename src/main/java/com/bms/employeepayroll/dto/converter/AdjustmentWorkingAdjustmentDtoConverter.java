package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.AdjustmentWorkingAdjustmentDto;
import com.bms.employeepayroll.model.WorkingAdjustment;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AdjustmentWorkingAdjustmentDtoConverter {
    public AdjustmentWorkingAdjustmentDto convert(WorkingAdjustment from) {
        return new AdjustmentWorkingAdjustmentDto(
                from.getId(),
                from.getAdjustmentAmount(),
                from.getAdjustmentPercentage(),
                Objects.requireNonNull(from.getWorkingHours()).getId(),
                Objects.requireNonNull(from.getSalaryPayment()).getId()
        );
    }

    public Set<AdjustmentWorkingAdjustmentDto> convert(Set<WorkingAdjustment> from) {
        return from.stream().map(this::convert).collect(Collectors.toSet());
    }
}
