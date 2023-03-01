package com.bms.employeepayroll.service.rules;

import com.bms.employeepayroll.core.exceptions.BusinessException;
import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.model.WorkingAdjustment;
import com.bms.employeepayroll.repository.WorkingAdjustmentRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class WorkingAdjustmentRules {
    private final WorkingAdjustmentRepository workingAdjustmentRepository;

    public WorkingAdjustmentRules(WorkingAdjustmentRepository workingAdjustmentRepository) {
        this.workingAdjustmentRepository = workingAdjustmentRepository;
    }

    public void checkIfWorkingAdjustmentListIsEmpty(Set<WorkingAdjustment> workingAdjustments) {
        if (workingAdjustments.isEmpty()) {
            throw new NotFoundException(BusinessMessages.WORKING_ADJUSTMENT_LIST_EMPTY);
        }
    }
}
