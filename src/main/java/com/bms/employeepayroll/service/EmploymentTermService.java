package com.bms.employeepayroll.service;

import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.core.utilities.services.rules.BusinessRules;
import com.bms.employeepayroll.core.utilities.services.rules.ValidationRules;
import com.bms.employeepayroll.dto.EmploymentTermDto;
import com.bms.employeepayroll.dto.converter.EmploymentTermDtoConverter;
import com.bms.employeepayroll.model.EmploymentTerm;
import com.bms.employeepayroll.repository.EmploymentTermRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import com.bms.employeepayroll.service.constants.ValidationMessages;
import com.bms.employeepayroll.service.requests.CreateEmploymentTermRequest;
import com.bms.employeepayroll.service.requests.UpdateEmploymentTermRequest;
import com.bms.employeepayroll.service.rules.EmploymentTermRules;
import com.bms.employeepayroll.service.validations.EmploymentTermValidation;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class EmploymentTermService {
    private final EmploymentTermRepository employmentTermRepository;
    private final EmployeeService employeeService;
    private final EmploymentTermDtoConverter converter;
    private final EmploymentTermRules rules;
    private final EmploymentTermValidation validation;

    public EmploymentTermService(EmploymentTermRepository employmentTermRepository,
                                 EmployeeService employeeService, EmploymentTermDtoConverter converter,
                                 EmploymentTermRules rules, EmploymentTermValidation validation) {
        this.employmentTermRepository = employmentTermRepository;
        this.employeeService = employeeService;
        this.converter = converter;
        this.rules = rules;
        this.validation = validation;
    }

    public void createEmploymentTerm(CreateEmploymentTermRequest request) {
        BusinessRules.run(() -> {
            rules.checkIfEmploymentTermEndsBeforeStarts(request.getStartDate(), Objects.requireNonNull(request.getEndDate()));
        });

        ValidationRules.run(() -> {
            validation.notEmpty(request.getAgreedSalary(), ValidationMessages.AGREED_SALARY_CANNOT_BE_EMPTY);
            validation.greaterThanOrEqual(
                    ((int) request.getAgreedSalary()),
                    0,
                    ValidationMessages.AGREED_SALARY_GREATER_THAN_ZERO
            );
        });

        var employmentTerm = new EmploymentTerm(
                request.getAgreedSalary(),
                request.getStartDate(),
                request.getEndDate(),
                employeeService.getEmployee(request.getEmployeeId())
        );

        employmentTermRepository.save(employmentTerm);
    }

    public void updateEmploymentTerm(String id, UpdateEmploymentTermRequest request) {
        BusinessRules.run(() -> {
            rules.checkIfEmploymentTermEndsBeforeStarts(request.getStartDate(), Objects.requireNonNull(request.getEndDate()));
        });

        ValidationRules.run(() -> {
            validation.notEmpty(request.getAgreedSalary(), ValidationMessages.AGREED_SALARY_CANNOT_BE_EMPTY);
            validation.greaterThanOrEqual(
                    ((int) request.getAgreedSalary()),
                    0,
                    ValidationMessages.AGREED_SALARY_GREATER_THAN_ZERO
            );
        });

        var employmentTerm = getEmploymentTerm(id);

        var updatedEmploymentTerm = new EmploymentTerm(
                employmentTerm.getId(),
                request.getAgreedSalary(),
                request.getStartDate(),
                request.getEndDate(),
                employeeService.getEmployee(request.getEmployeeId())
        );

        employmentTermRepository.save(updatedEmploymentTerm);
    }

    public void deleteEmploymentTerm(String id) {
        employmentTermRepository.delete(getEmploymentTerm(id));
    }

    public EmploymentTermDto getEmploymentTermById(String id) {
        var employmentTerm = getEmploymentTerm(id);

        return converter.convert(employmentTerm);
    }

    public Set<EmploymentTermDto> getAllEmploymentTerms() {
        var employmentTerms = new HashSet<>(employmentTermRepository.findAll());

        BusinessRules.run(() -> rules.checkIfEmploymentTermListIsEmpty(employmentTerms));

        return converter.convert(employmentTerms);
    }

    protected EmploymentTerm getEmploymentTerm(String id) {
        var employmentTerm = employmentTermRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessMessages.EMPLOYMENT_TERM_NOT_FOUND));

        return employmentTerm;
    }
}
