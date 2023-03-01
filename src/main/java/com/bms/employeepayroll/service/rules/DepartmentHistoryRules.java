package com.bms.employeepayroll.service.rules;

import com.bms.employeepayroll.core.exceptions.BusinessException;
import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.model.DepartmentHistory;
import com.bms.employeepayroll.repository.DepartmentHistoryRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
public class DepartmentHistoryRules {
    private final DepartmentHistoryRepository repository;

    public DepartmentHistoryRules(DepartmentHistoryRepository repository) {
        this.repository = repository;
    }

    public void checkIfDepartmentHistoryEndsBeforeStarts(LocalDate startDate, LocalDate endDate) {
        if (endDate != null && endDate.isBefore(startDate)) {
            throw new BusinessException(BusinessMessages.DEPARTMENT_HISTORY_ENDS_BEFORE_STARTS);
        }
    }

    public void checkIfDepartmentHistoryListIsEmpty(Set<DepartmentHistory> departmentHistories) {
        if (departmentHistories.isEmpty()) {
            throw new NotFoundException(BusinessMessages.DEPARTMENT_HISTORY_LIST_EMPTY);
        }
    }
}
