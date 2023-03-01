package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.AdjustmentAmountDto;
import com.bms.employeepayroll.model.AdjustmentAmount;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AdjustmentAmountDtoConverter {
    public AdjustmentAmountDto convert(AdjustmentAmount from) {
        return new AdjustmentAmountDto(
                from.getId(),
                from.getAmount(),
                from.getPercentage(),
                Objects.requireNonNull(from.getAdjustment()).getId(),
                Objects.requireNonNull(from.getSalaryPayment()).getId()
        );
    }

    public Set<AdjustmentAmountDto> convert(Set<AdjustmentAmount> from) {
        return from.stream().map(this::convert).collect(Collectors.toSet());
    }
}
