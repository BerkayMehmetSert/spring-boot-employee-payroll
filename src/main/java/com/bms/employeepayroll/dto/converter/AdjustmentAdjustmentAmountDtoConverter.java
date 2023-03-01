package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.AdjustmentAdjustmentAmountDto;
import com.bms.employeepayroll.model.AdjustmentAmount;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AdjustmentAdjustmentAmountDtoConverter {
    public AdjustmentAdjustmentAmountDto convert(AdjustmentAmount from) {
        return new AdjustmentAdjustmentAmountDto(
                from.getId(),
                from.getAmount(),
                from.getPercentage(),
                Objects.requireNonNull(from.getSalaryPayment()).getId()
        );
    }

    public Set<AdjustmentAdjustmentAmountDto> convert(Set<AdjustmentAmount> from) {
        return from.stream().map(this::convert).collect(Collectors.toSet());
    }
}
