package com.bms.employeepayroll.service.rules;

import com.bms.employeepayroll.core.exceptions.BusinessException;
import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.model.EmploymentTerm;
import com.bms.employeepayroll.repository.EmploymentTermRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
public class EmploymentTermRules {
    private final EmploymentTermRepository repository;

    public EmploymentTermRules(EmploymentTermRepository repository) {
        this.repository = repository;
    }

    public void checkIfEmploymentTermEndsBeforeStarts(LocalDate startDate, LocalDate endDate) {
        if (endDate != null && endDate.isBefore(startDate))
            throw new BusinessException(BusinessMessages.EMPLOYMENT_TERM_ENDS_BEFORE_STARTS);
    }

    public void checkIfEmploymentTermListIsEmpty(Set<EmploymentTerm> employmentTerms) {
        if (employmentTerms.isEmpty()) throw new NotFoundException(BusinessMessages.EMPLOYMENT_TERM_LIST_EMPTY);
    }
}
