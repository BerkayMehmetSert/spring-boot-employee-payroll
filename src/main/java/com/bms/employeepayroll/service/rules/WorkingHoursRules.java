package com.bms.employeepayroll.service.rules;

import com.bms.employeepayroll.core.exceptions.BusinessException;
import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.model.WorkingHours;
import com.bms.employeepayroll.repository.WorkingHoursRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
public class WorkingHoursRules {
    private final WorkingHoursRepository repository;

    public WorkingHoursRules(WorkingHoursRepository repository) {
        this.repository = repository;
    }

    public void checkIfWorkingHoursEndsBeforeStarts(LocalDateTime startTime, LocalDateTime endTime) {
        if (endTime != null && endTime.isBefore(startTime)) {
            throw new BusinessException(BusinessMessages.WORKING_HOURS_ENDS_BEFORE_STARTS);
        }
    }

    public void checkIfWorkingHoursListIsEmpty(Set<WorkingHours> workingHours) {
        if (workingHours.isEmpty()) {
            throw new NotFoundException(BusinessMessages.WORKING_HOURS_LIST_EMPTY);
        }
    }
}
