package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.EmployeeWorkingHoursDto;
import com.bms.employeepayroll.model.WorkingHours;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EmployeeWorkingHoursDtoConverter {
    public EmployeeWorkingHoursDto convert(WorkingHours from) {
        return new EmployeeWorkingHoursDto(
                from.getId(),
                from.getStartTime(),
                from.getEndTime()
        );
    }

    public Set<EmployeeWorkingHoursDto> convert(Set<WorkingHours> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
