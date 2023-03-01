package com.bms.employeepayroll.service.rules;

import com.bms.employeepayroll.core.exceptions.BusinessException;
import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.model.JobTitle;
import com.bms.employeepayroll.repository.JobTitleRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class JobTitleRules {
    private final JobTitleRepository repository;

    public JobTitleRules(JobTitleRepository repository) {
        this.repository = repository;
    }

    public void checkIfJobTitleExists(String title){
        var jobTitle = repository.existsByTitleIgnoreCase(title);

        if (jobTitle) throw new BusinessException(BusinessMessages.JOB_TITLE_ALREADY_EXISTS);
    }

    public void checkIfJobTitleListIsEmpty(Set<JobTitle> jobTitles){
        if (jobTitles.isEmpty()) throw new NotFoundException(BusinessMessages.JOB_TITLE_LIST_IS_EMPTY);
    }
}
