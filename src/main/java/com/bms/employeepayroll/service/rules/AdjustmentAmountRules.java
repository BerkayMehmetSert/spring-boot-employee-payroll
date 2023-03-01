package com.bms.employeepayroll.service.rules;

import com.bms.employeepayroll.core.exceptions.BusinessException;
import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.model.AdjustmentAmount;
import com.bms.employeepayroll.repository.AdjustmentAmountRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AdjustmentAmountRules {
    private final AdjustmentAmountRepository repository;

    public AdjustmentAmountRules(AdjustmentAmountRepository repository) {
        this.repository = repository;
    }

    public void checkIfAdjustmentAmountListIsEmpty(Set<AdjustmentAmount> adjustmentAmounts) {
        if (adjustmentAmounts.isEmpty()) {
            throw new NotFoundException(BusinessMessages.ADJUSTMENT_AMOUNT_LIST_EMPTY);
        }
    }
}
