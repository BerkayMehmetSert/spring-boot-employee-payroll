package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.EmployeeJobHistoryDto;
import com.bms.employeepayroll.model.JobHistory;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EmployeeJobHistoryDtoConverter {
    public EmployeeJobHistoryDto convert(JobHistory from) {
        return new EmployeeJobHistoryDto(
                from.getId(),
                from.getStartDate(),
                Objects.requireNonNull(from.getEndDate()),
                Objects.requireNonNull(from.getJobTitle()).getId()
        );
    }

    public Set<EmployeeJobHistoryDto> convert(Set<JobHistory> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
