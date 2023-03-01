package com.bms.employeepayroll.service;

import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.core.utilities.services.rules.BusinessRules;
import com.bms.employeepayroll.core.utilities.services.rules.ValidationRules;
import com.bms.employeepayroll.dto.SalaryPaymentDto;
import com.bms.employeepayroll.dto.converter.SalaryPaymentDtoConverter;
import com.bms.employeepayroll.model.SalaryPayment;
import com.bms.employeepayroll.repository.SalaryPaymentRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import com.bms.employeepayroll.service.constants.ValidationMessages;
import com.bms.employeepayroll.service.requests.CreateSalaryPaymentRequest;
import com.bms.employeepayroll.service.requests.UpdateSalaryPaymentRequest;
import com.bms.employeepayroll.service.rules.SalaryPaymentRules;
import com.bms.employeepayroll.service.validations.SalaryPaymentValidation;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SalaryPaymentService {
    private final SalaryPaymentRepository salaryPaymentRepository;
    private final EmployeeService employeeService;
    private final SalaryPaymentDtoConverter converter;
    private final SalaryPaymentRules rules;
    private final SalaryPaymentValidation validation;

    public SalaryPaymentService(SalaryPaymentRepository salaryPaymentRepository,
                                EmployeeService employeeService, SalaryPaymentDtoConverter converter,
                                SalaryPaymentRules rules, SalaryPaymentValidation validation) {
        this.salaryPaymentRepository = salaryPaymentRepository;
        this.employeeService = employeeService;
        this.converter = converter;
        this.rules = rules;
        this.validation = validation;
    }

    public void createSalaryPayment(CreateSalaryPaymentRequest request) {
        ValidationRules.run(() -> {
            validation.notEmpty(request.getGrossSalary(), ValidationMessages.GROSS_SALARY_CANNOT_BE_EMPTY);
            validation.notEmpty(request.getNetSalary(), ValidationMessages.NET_SALARY_CANNOT_BE_EMPTY);
            validation.greaterThanOrEqual(
                    ((int) request.getGrossSalary()),
                    0,
                    ValidationMessages.AGREED_SALARY_GREATER_THAN_ZERO
            );
            validation.greaterThanOrEqual(
                    ((int) request.getNetSalary()),
                    0,
                    ValidationMessages.AGREED_SALARY_GREATER_THAN_ZERO
            );
        });

        var salaryPayment = new SalaryPayment(
                request.getGrossSalary(),
                request.getNetSalary(),
                request.getSalaryPeriod(),
                employeeService.getEmployee(request.getEmployeeId())
        );

        salaryPaymentRepository.save(salaryPayment);
    }

    public void updateSalaryPayment(String id, UpdateSalaryPaymentRequest request) {
        ValidationRules.run(() -> {
            validation.notEmpty(request.getGrossSalary(), ValidationMessages.GROSS_SALARY_CANNOT_BE_EMPTY);
            validation.notEmpty(request.getNetSalary(), ValidationMessages.NET_SALARY_CANNOT_BE_EMPTY);
            validation.greaterThanOrEqual(
                    ((int) request.getGrossSalary()),
                    0,
                    ValidationMessages.AGREED_SALARY_GREATER_THAN_ZERO
            );
            validation.greaterThanOrEqual(
                    ((int) request.getNetSalary()),
                    0,
                    ValidationMessages.AGREED_SALARY_GREATER_THAN_ZERO
            );
        });

        var salaryPayment = getSalaryPayment(id);

        var updatedSalaryPayment = new SalaryPayment(
                salaryPayment.getId(),
                request.getGrossSalary(),
                request.getNetSalary(),
                request.getSalaryPeriod(),
                salaryPayment.getWorkingAdjustments(),
                employeeService.getEmployee(request.getEmployeeId())
        );

        salaryPaymentRepository.save(updatedSalaryPayment);
    }

    public void deleteSalaryPayment(String id) {
        salaryPaymentRepository.delete(getSalaryPayment(id));
    }

    public SalaryPaymentDto getSalaryPaymentById(String id) {
        var salaryPayment = getSalaryPayment(id);

        return converter.convert(salaryPayment);
    }

    public Set<SalaryPaymentDto> getAllSalaryPayments() {
        var salaryPayments = new HashSet<>(salaryPaymentRepository.findAll());

        BusinessRules.run(() -> rules.checkIfSalaryPaymentListIsEmpty(salaryPayments));

        return converter.convert(salaryPayments);
    }

    protected SalaryPayment getSalaryPayment(String id) {
        var salaryPayment = salaryPaymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessMessages.SALARY_PAYMENT_NOT_FOUND));

        return salaryPayment;
    }
}
