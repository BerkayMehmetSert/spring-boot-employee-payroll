package com.bms.employeepayroll.core.exceptions.handlers;

import com.bms.employeepayroll.core.exceptions.BusinessException;
import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.core.exceptions.ValidationException;
import org.springframework.http.ProblemDetail;

public abstract class BaseExceptionHandler {
    public abstract ProblemDetail handleException(BusinessException exception);
    public abstract ProblemDetail handleException(NotFoundException exception);
    public abstract ProblemDetail handleException(ValidationException exception);
    public abstract ProblemDetail handleException(Exception exception);
}
