package com.bms.employeepayroll.service.rules;

import com.bms.employeepayroll.core.exceptions.BusinessException;
import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.model.Adjustment;
import com.bms.employeepayroll.repository.AdjustmentRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AdjustmentRules {
    private final AdjustmentRepository adjustmentRepository;

    public AdjustmentRules(AdjustmentRepository adjustmentRepository) {
        this.adjustmentRepository = adjustmentRepository;
    }

    public void checkIfAdjustmentExists(String name) {
        var adjustment = adjustmentRepository.existsByNameIgnoreCase(name);

        if (adjustment) {
            throw new BusinessException(BusinessMessages.ADJUSTMENT_ALREADY_EXISTS);
        }
    }

    public void checkIfAdjustmentListIsEmpty(Set<Adjustment> adjustments) {
        if (adjustments.isEmpty()) {
            throw new NotFoundException(BusinessMessages.ADJUSTMENT_LIST_EMPTY);
        }
    }
}
