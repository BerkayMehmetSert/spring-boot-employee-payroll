package com.bms.employeepayroll.core.exceptions.handlers;

import com.bms.employeepayroll.core.exceptions.BusinessException;
import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.core.exceptions.ValidationException;
import com.bms.employeepayroll.core.exceptions.problemdetails.BusinessProblemDetails;
import com.bms.employeepayroll.core.exceptions.problemdetails.InternalServerProblemDetails;
import com.bms.employeepayroll.core.exceptions.problemdetails.NotFoundProblemDetails;
import com.bms.employeepayroll.core.exceptions.problemdetails.ValidationProblemDetails;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HttpExceptionHandler extends BaseExceptionHandler {
    @Override
    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleException(BusinessException exception) {
        return new BusinessProblemDetails(exception.getMessage());
    }

    @Override
    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleException(NotFoundException exception) {
        return new NotFoundProblemDetails(exception.getMessage());
    }

    @Override
    @ExceptionHandler(ValidationException.class)
    public ProblemDetail handleException(ValidationException exception) {
        return new ValidationProblemDetails(exception.getMessage());
    }

    @Override
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception exception) {
        return new InternalServerProblemDetails(exception.getMessage());
    }
}
