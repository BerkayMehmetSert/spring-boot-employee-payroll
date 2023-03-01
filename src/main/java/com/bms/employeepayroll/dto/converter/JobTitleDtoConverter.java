package com.bms.employeepayroll.dto.converter;

import com.bms.employeepayroll.dto.JobTitleDto;
import com.bms.employeepayroll.model.JobTitle;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JobTitleDtoConverter {
    private final JobTitleEmployeeDtoConverter jobTitleEmployeeDtoConverter;
    private final JobTitleJobHistoryDtoConverter jobTitleJobHistoryDtoConverter;

    public JobTitleDtoConverter(JobTitleEmployeeDtoConverter jobTitleEmployeeDtoConverter,
                                JobTitleJobHistoryDtoConverter jobTitleJobHistoryDtoConverter) {
        this.jobTitleEmployeeDtoConverter = jobTitleEmployeeDtoConverter;
        this.jobTitleJobHistoryDtoConverter = jobTitleJobHistoryDtoConverter;
    }

    public JobTitleDto convert(JobTitle from) {
        return new JobTitleDto(
                from.getId(),
                from.getTitle(),
                from.getEmployees() != null ? jobTitleEmployeeDtoConverter.convert(from.getEmployees()) : null,
                from.getJobHistories() != null ? jobTitleJobHistoryDtoConverter.convert(from.getJobHistories()) : null
        );
    }

    public Set<JobTitleDto> convert(Set<JobTitle> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
