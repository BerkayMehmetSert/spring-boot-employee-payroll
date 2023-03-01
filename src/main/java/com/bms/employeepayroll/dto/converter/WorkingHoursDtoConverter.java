package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.WorkingHoursDto;
import com.bms.employeepayroll.model.WorkingHours;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class WorkingHoursDtoConverter {
    private final WorkingHoursWorkingAdjustmentDtoConverter workingHoursWorkingAdjustmentDtoConverter;

    public WorkingHoursDtoConverter(WorkingHoursWorkingAdjustmentDtoConverter workingHoursWorkingAdjustmentDtoConverter) {
        this.workingHoursWorkingAdjustmentDtoConverter = workingHoursWorkingAdjustmentDtoConverter;
    }

    public WorkingHoursDto convert(WorkingHours from) {
        return new WorkingHoursDto(
                from.getId(),
                from.getStartTime(),
                from.getEndTime(),
                Objects.requireNonNull(from.getEmployee()).getId(),
                from.getWorkingAdjustments() != null ? workingHoursWorkingAdjustmentDtoConverter.convert(from.getWorkingAdjustments()) : null
        );
    }

    public Set<WorkingHoursDto> convert(Set<WorkingHours> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
