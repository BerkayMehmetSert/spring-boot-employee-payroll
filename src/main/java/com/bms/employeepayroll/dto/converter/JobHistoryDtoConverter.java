package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.JobHistoryDto;
import com.bms.employeepayroll.model.JobHistory;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JobHistoryDtoConverter {
    public JobHistoryDto convert(JobHistory from) {
        return new JobHistoryDto(
                from.getId(),
                from.getStartDate(),
                Objects.requireNonNull(from.getEndDate()),
                Objects.requireNonNull(from.getEmployee()).getId(),
                Objects.requireNonNull(from.getJobTitle()).getId()
        );
    }

    public Set<JobHistoryDto> convert(Set<JobHistory> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
