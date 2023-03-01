package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.JobTitleJobHistoryDto;
import com.bms.employeepayroll.model.JobHistory;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JobTitleJobHistoryDtoConverter {
    public JobTitleJobHistoryDto convert(JobHistory from) {
        return new JobTitleJobHistoryDto(
                from.getId(),
                from.getStartDate(),
                Objects.requireNonNull(from.getEndDate()),
                Objects.requireNonNull(from.getEmployee()).getId()
        );
    }

    public Set<JobTitleJobHistoryDto> convert(Set<JobHistory> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
