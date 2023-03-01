package com.bms.employeepayroll.service.validations;

import com.bms.employeepayroll.core.exceptions.ValidationException;
import com.bms.employeepayroll.core.utilities.services.validations.ValidationService;
import org.springframework.stereotype.Component;

@Component
public class EmploymentTermValidation extends ValidationService {
    public void notEmpty(Double value, String message) {
        if (value == null) throw new ValidationException(message);
    }
}
