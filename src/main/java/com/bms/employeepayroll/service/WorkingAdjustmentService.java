package com.bms.employeepayroll.service;

import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.core.utilities.services.rules.BusinessRules;
import com.bms.employeepayroll.core.utilities.services.rules.ValidationRules;
import com.bms.employeepayroll.dto.WorkingAdjustmentDto;
import com.bms.employeepayroll.dto.converter.WorkingAdjustmentDtoConverter;
import com.bms.employeepayroll.model.WorkingAdjustment;
import com.bms.employeepayroll.repository.WorkingAdjustmentRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import com.bms.employeepayroll.service.constants.ValidationMessages;
import com.bms.employeepayroll.service.requests.CreateWorkingAdjustmentRequest;
import com.bms.employeepayroll.service.requests.UpdateWorkingAdjustmentRequest;
import com.bms.employeepayroll.service.rules.WorkingAdjustmentRules;
import com.bms.employeepayroll.service.validations.WorkingAdjustmentValidation;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class WorkingAdjustmentService {
    private final WorkingAdjustmentRepository workingAdjustmentRepository;
    private final WorkingHoursService workingHoursService;
    private final AdjustmentService adjustmentService;
    private final SalaryPaymentService salaryPaymentService;
    private final WorkingAdjustmentDtoConverter converter;
    private final WorkingAdjustmentRules rules;
    private final WorkingAdjustmentValidation validation;

    public WorkingAdjustmentService(WorkingAdjustmentRepository workingAdjustmentRepository,
                                    WorkingHoursService workingHoursService,
                                    AdjustmentService adjustmentService,
                                    SalaryPaymentService salaryPaymentService,
                                    WorkingAdjustmentDtoConverter converter,
                                    WorkingAdjustmentRules rules, WorkingAdjustmentValidation validation) {
        this.workingAdjustmentRepository = workingAdjustmentRepository;
        this.workingHoursService = workingHoursService;
        this.adjustmentService = adjustmentService;
        this.salaryPaymentService = salaryPaymentService;
        this.converter = converter;
        this.rules = rules;
        this.validation = validation;
    }

    public void createWorkingAdjustment(CreateWorkingAdjustmentRequest request) {
        ValidationRules.run(() -> {
            validation.notEmpty(request.getAdjustmentAmount(), ValidationMessages.ADJUSTMENT_AMOUNT_CANNOT_BE_EMPTY);
            validation.notEmpty(request.getAdjustmentPercentage(), ValidationMessages.ADJUSTMENT_PERCENTAGE_CANNOT_BE_EMPTY);
            validation.greaterThanOrEqual(
                    ((int) request.getAdjustmentPercentage()),
                    0,
                    ValidationMessages.ADJUSTMENT_PERCENTAGE_GREATER_THAN_ZERO
            );
            validation.greaterThanOrEqual(
                    ((int) request.getAdjustmentAmount()),
                    0,
                    ValidationMessages.ADJUSTMENT_AMOUNT_GREATER_THAN_ZERO
            );
        });

        var workingAdjustment = new WorkingAdjustment(
                request.getAdjustmentAmount(),
                request.getAdjustmentPercentage(),
                workingHoursService.getWorkingHours(request.getWorkingHoursId()),
                adjustmentService.getAdjustment(request.getAdjustmentId()),
                salaryPaymentService.getSalaryPayment(request.getSalaryPaymentId())
        );

        workingAdjustmentRepository.save(workingAdjustment);
    }

    public void updateWorkingAdjustment(String id, UpdateWorkingAdjustmentRequest request) {
        ValidationRules.run(() -> {
            validation.notEmpty(request.getAdjustmentAmount(), ValidationMessages.ADJUSTMENT_AMOUNT_CANNOT_BE_EMPTY);
            validation.notEmpty(request.getAdjustmentPercentage(), ValidationMessages.ADJUSTMENT_PERCENTAGE_CANNOT_BE_EMPTY);
            validation.greaterThanOrEqual(
                    ((int) request.getAdjustmentPercentage()),
                    0,
                    ValidationMessages.ADJUSTMENT_PERCENTAGE_GREATER_THAN_ZERO
            );
            validation.greaterThanOrEqual(
                    ((int) request.getAdjustmentAmount()),
                    0,
                    ValidationMessages.ADJUSTMENT_AMOUNT_GREATER_THAN_ZERO
            );
        });

        var workingAdjustment = getWorkingAdjustment(id);

        var updatedWorkingAdjustment = new WorkingAdjustment(
                workingAdjustment.getId(),
                request.getAdjustmentAmount(),
                request.getAdjustmentPercentage(),
                workingHoursService.getWorkingHours(request.getWorkingHoursId()),
                adjustmentService.getAdjustment(request.getAdjustmentId()),
                salaryPaymentService.getSalaryPayment(request.getSalaryPaymentId())
        );

        workingAdjustmentRepository.save(updatedWorkingAdjustment);
    }

    public void deleteWorkingAdjustment(String id) {
        workingAdjustmentRepository.delete(getWorkingAdjustment(id));
    }

    public WorkingAdjustmentDto getWorkingAdjustmentById(String id) {
        return converter.convert(getWorkingAdjustment(id));
    }

    public Set<WorkingAdjustmentDto> getAllWorkingAdjustments() {
        var workingAdjustments = new HashSet<>(workingAdjustmentRepository.findAll());

        BusinessRules.run(() -> rules.checkIfWorkingAdjustmentListIsEmpty(workingAdjustments));

        return converter.convert(workingAdjustments);
    }

    protected WorkingAdjustment getWorkingAdjustment(String id) {
        var workingAdjustment = workingAdjustmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessMessages.WORKING_ADJUSTMENT_NOT_FOUND));

        return workingAdjustment;
    }
}
