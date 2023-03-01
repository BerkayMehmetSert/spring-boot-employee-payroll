package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.AdjustmentDto;
import com.bms.employeepayroll.model.Adjustment;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AdjustmentDtoConverter {
    private final AdjustmentAdjustmentAmountDtoConverter adjustmentAmountDtoConverter;
    private final AdjustmentWorkingAdjustmentDtoConverter workingAdjustmentDtoConverter;

    public AdjustmentDtoConverter(AdjustmentAdjustmentAmountDtoConverter adjustmentAmountDtoConverter,
                                  AdjustmentWorkingAdjustmentDtoConverter workingAdjustmentDtoConverter) {
        this.adjustmentAmountDtoConverter = adjustmentAmountDtoConverter;
        this.workingAdjustmentDtoConverter = workingAdjustmentDtoConverter;
    }

    public AdjustmentDto convert(Adjustment from) {
        return new AdjustmentDto(
                from.getId(),
                from.getName(),
                from.getPercentage(),
                from.isWorkingHoursAdjustment(),
                from.isOtherAdjustment(),
                from.getWorkingAdjustments() != null ? workingAdjustmentDtoConverter.convert(from.getWorkingAdjustments()) : null,
                from.getAdjustmentAmounts() != null ? adjustmentAmountDtoConverter.convert(from.getAdjustmentAmounts()) : null
        );
    }

    public Set<AdjustmentDto> convert(Set<Adjustment> from) {
        return from.stream().map(this::convert).collect(Collectors.toSet());
    }
}
