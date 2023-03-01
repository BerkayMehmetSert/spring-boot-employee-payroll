package com.bms.employeepayroll.service;

import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.core.utilities.services.rules.BusinessRules;
import com.bms.employeepayroll.core.utilities.services.rules.ValidationRules;
import com.bms.employeepayroll.dto.JobTitleDto;
import com.bms.employeepayroll.dto.converter.JobTitleDtoConverter;
import com.bms.employeepayroll.model.JobTitle;
import com.bms.employeepayroll.repository.JobTitleRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import com.bms.employeepayroll.service.constants.ValidationMessages;
import com.bms.employeepayroll.service.rules.JobTitleRules;
import com.bms.employeepayroll.service.validations.JobTitleValidation;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JobTitleService {
    private final JobTitleRepository jobTitleRepository;
    private final JobTitleDtoConverter converter;
    private final JobTitleRules rules;
    private final JobTitleValidation validation;

    public JobTitleService(JobTitleRepository jobTitleRepository, JobTitleDtoConverter converter,
                           JobTitleRules rules, JobTitleValidation validation) {
        this.jobTitleRepository = jobTitleRepository;
        this.converter = converter;
        this.rules = rules;
        this.validation = validation;
    }

    public void createJobTitle(String title) {
        BusinessRules.run(() -> rules.checkIfJobTitleExists(title));
        ValidationRules.run(() -> validation.notEmpty(title, ValidationMessages.TITLE_CANNOT_BE_EMPTY));

        var jobTitle = new JobTitle(title);

        jobTitleRepository.save(jobTitle);
    }

    public void updateJobTitle(String id, String title) {
        var jobTitle = getJobTitle(id);

        ValidationRules.run(() -> validation.notEmpty(title, ValidationMessages.TITLE_CANNOT_BE_EMPTY));

        var updatedJobTitle = new JobTitle(
                jobTitle.getId(),
                title,
                jobTitle.getEmployees(),
                jobTitle.getJobHistories()
        );

        jobTitleRepository.save(updatedJobTitle);
    }

    public void deleteJobTitle(String id) {
        jobTitleRepository.delete(getJobTitle(id));
    }

    public JobTitleDto getJobTitleById(String id) {
        var jobTitle = getJobTitle(id);

        return converter.convert(jobTitle);
    }

    public Set<JobTitleDto> getAllJobTitles() {
        var jobTitles = new HashSet<>(jobTitleRepository.findAll());

        BusinessRules.run(() -> rules.checkIfJobTitleListIsEmpty(jobTitles));

        return converter.convert(jobTitles);
    }

    protected JobTitle getJobTitle(String id) {
        var jobTitle = jobTitleRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(BusinessMessages.JOB_TITLE_NOT_FOUND);
                });

        return jobTitle;
    }
}
