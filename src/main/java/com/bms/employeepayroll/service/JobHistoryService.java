package com.bms.employeepayroll.service;

import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.core.utilities.services.rules.BusinessRules;
import com.bms.employeepayroll.dto.JobHistoryDto;
import com.bms.employeepayroll.dto.converter.JobHistoryDtoConverter;
import com.bms.employeepayroll.model.JobHistory;
import com.bms.employeepayroll.repository.JobHistoryRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import com.bms.employeepayroll.service.requests.CreateJobHistoryRequest;
import com.bms.employeepayroll.service.requests.UpdateJobHistoryRequest;
import com.bms.employeepayroll.service.rules.JobHistoryRules;
import com.bms.employeepayroll.service.validations.JobHistoryValidation;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JobHistoryService {
    private final JobHistoryRepository jobHistoryRepository;
    private final EmployeeService employeeService;
    private final JobTitleService jobTitleService;
    private final JobHistoryDtoConverter converter;
    private final JobHistoryRules rules;
    private final JobHistoryValidation validation;

    public JobHistoryService(JobHistoryRepository jobHistoryRepository,
                             EmployeeService employeeService, JobTitleService jobTitleService,
                             JobHistoryDtoConverter converter, JobHistoryRules rules,
                             JobHistoryValidation validation) {
        this.jobHistoryRepository = jobHistoryRepository;
        this.employeeService = employeeService;
        this.jobTitleService = jobTitleService;
        this.converter = converter;
        this.rules = rules;
        this.validation = validation;
    }

    public void createJobHistory(CreateJobHistoryRequest request) {
        BusinessRules.run(() -> {
            rules.checkIfJobHistoryEndsBeforeStarts(request.getStartDate(), request.getEndDate());
        });

        var jobHistory = new JobHistory(
                request.getStartDate(),
                request.getEndDate(),
                employeeService.getEmployee(request.getEmployeeId()),
                jobTitleService.getJobTitle(request.getJobTitleId())
        );

        jobHistoryRepository.save(jobHistory);
    }

    public void updateJobHistory(String id, UpdateJobHistoryRequest request) {
        BusinessRules.run(() -> {
            rules.checkIfJobHistoryEndsBeforeStarts(request.getStartDate(), request.getEndDate());
        });

        var jobHistory = getJobHistory(id);

        var updatedJobHistory = new JobHistory(
                jobHistory.getId(),
                request.getStartDate(),
                request.getEndDate(),
                employeeService.getEmployee(request.getEmployeeId()),
                jobTitleService.getJobTitle(request.getJobTitleId())
        );

        jobHistoryRepository.save(updatedJobHistory);
    }

    public void deleteJobHistory(String id) {
        jobHistoryRepository.delete(getJobHistory(id));
    }

    public JobHistoryDto getJobHistoryById(String id) {
        return converter.convert(getJobHistory(id));
    }

    public Set<JobHistoryDto> getAllJobHistories() {
        var jobHistories = new HashSet<>(jobHistoryRepository.findAll());

        BusinessRules.run(() -> rules.checkIfJobHistoryListIsEmpty(jobHistories));

        return converter.convert(jobHistories);
    }

    protected JobHistory getJobHistory(String id) {
        var jobHistory = jobHistoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessMessages.JOB_HISTORY_NOT_FOUND));

        return jobHistory;
    }
}
