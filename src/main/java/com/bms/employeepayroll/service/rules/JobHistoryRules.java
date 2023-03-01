package com.bms.employeepayroll.service.rules;

import com.bms.employeepayroll.core.exceptions.BusinessException;
import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.model.JobHistory;
import com.bms.employeepayroll.repository.JobHistoryRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
public class JobHistoryRules {
    private final JobHistoryRepository jobHistoryRepository;

    public JobHistoryRules(JobHistoryRepository jobHistoryRepository) {
        this.jobHistoryRepository = jobHistoryRepository;
    }

    public void checkIfJobHistoryEndsBeforeStarts(LocalDate startDate, LocalDate endDate) {
        if (endDate!=null&&endDate.isBefore(startDate)) {
            throw new BusinessException(BusinessMessages.JOB_HISTORY_ENDS_BEFORE_STARTS);
        }
    }

    public void checkIfJobHistoryListIsEmpty(Set<JobHistory> jobHistories) {
        if (jobHistories.isEmpty()) {
            throw new NotFoundException(BusinessMessages.JOB_HISTORY_LIST_EMPTY);
        }
    }
}
