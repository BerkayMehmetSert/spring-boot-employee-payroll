package com.bms.employeepayroll.service.validations;

import com.bms.employeepayroll.core.exceptions.ValidationException;
import com.bms.employeepayroll.core.utilities.services.validations.ValidationService;
import org.springframework.stereotype.Component;

@Component
public class AdjustmentValidation extends ValidationService {
    public void greaterThan(double value, double min, String message) {
        if (value < min)
            throw new ValidationException(message);
    }
}
