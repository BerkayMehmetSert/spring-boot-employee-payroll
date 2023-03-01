package com.bms.employeepayroll.service;

import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.core.utilities.services.rules.BusinessRules;
import com.bms.employeepayroll.core.utilities.services.rules.ValidationRules;
import com.bms.employeepayroll.dto.AdjustmentAmountDto;
import com.bms.employeepayroll.dto.converter.AdjustmentAmountDtoConverter;
import com.bms.employeepayroll.model.AdjustmentAmount;
import com.bms.employeepayroll.repository.AdjustmentAmountRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import com.bms.employeepayroll.service.constants.ValidationMessages;
import com.bms.employeepayroll.service.requests.CreateAdjustmentAmountRequest;
import com.bms.employeepayroll.service.requests.CreateAdjustmentRequest;
import com.bms.employeepayroll.service.requests.UpdateAdjustmentAmountRequest;
import com.bms.employeepayroll.service.rules.AdjustmentAmountRules;
import com.bms.employeepayroll.service.validations.AdjustmentAmountValidation;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AdjustmentAmountService {
    private final AdjustmentAmountRepository adjustmentAmountRepository;
    private final AdjustmentService adjustmentService;
    private final SalaryPaymentService salaryPaymentService;
    private final AdjustmentAmountDtoConverter converter;
    private final AdjustmentAmountRules rules;
    private final AdjustmentAmountValidation validation;

    public AdjustmentAmountService(AdjustmentAmountRepository adjustmentAmountRepository,
                                   AdjustmentService adjustmentService,
                                   SalaryPaymentService salaryPaymentService,
                                   AdjustmentAmountDtoConverter converter,
                                   AdjustmentAmountRules rules, AdjustmentAmountValidation validation) {
        this.adjustmentAmountRepository = adjustmentAmountRepository;
        this.adjustmentService = adjustmentService;
        this.salaryPaymentService = salaryPaymentService;
        this.converter = converter;
        this.rules = rules;
        this.validation = validation;
    }

    public void createAdjustmentAmount(CreateAdjustmentAmountRequest request) {
        ValidationRules.run(() -> {
            validation.notEmpty(request.getAmount(), ValidationMessages.ADJUSTMENT_AMOUNT_CANNOT_BE_EMPTY);
            validation.notEmpty(request.getPercentage(), ValidationMessages.ADJUSTMENT_PERCENTAGE_CANNOT_BE_EMPTY);
            validation.greaterThanOrEqual(
                    ((int) request.getAmount()),
                    0,
                    ValidationMessages.ADJUSTMENT_AMOUNT_GREATER_THAN_ZERO
            );
            validation.greaterThanOrEqual(
                    ((int) request.getPercentage()),
                    0,
                    ValidationMessages.ADJUSTMENT_PERCENTAGE_GREATER_THAN_ZERO
            );
        });

        var adjustmentAmount = new AdjustmentAmount(
                request.getAmount(),
                request.getPercentage(),
                adjustmentService.getAdjustment(request.getAdjustmentId()),
                salaryPaymentService.getSalaryPayment(request.getSalaryPaymentId())
        );

        adjustmentAmountRepository.save(adjustmentAmount);
    }

    public void updateAdjustmentAmount(String id, UpdateAdjustmentAmountRequest request) {
        ValidationRules.run(() -> {
            validation.notEmpty(request.getAmount(), ValidationMessages.ADJUSTMENT_AMOUNT_CANNOT_BE_EMPTY);
            validation.notEmpty(request.getPercentage(), ValidationMessages.ADJUSTMENT_PERCENTAGE_CANNOT_BE_EMPTY);
            validation.greaterThanOrEqual(
                    ((int) request.getAmount()),
                    0,
                    ValidationMessages.ADJUSTMENT_AMOUNT_GREATER_THAN_ZERO
            );
            validation.greaterThanOrEqual(
                    ((int) request.getPercentage()),
                    0,
                    ValidationMessages.ADJUSTMENT_PERCENTAGE_GREATER_THAN_ZERO
            );
        });

        var adjustmentAmount = getAdjustmentAmount(id);

        var updatedAdjustmentAmount = new AdjustmentAmount(
                adjustmentAmount.getId(),
                request.getAmount(),
                request.getPercentage(),
                adjustmentService.getAdjustment(request.getAdjustmentId()),
                salaryPaymentService.getSalaryPayment(request.getSalaryPaymentId())
        );

        adjustmentAmountRepository.save(updatedAdjustmentAmount);
    }

    public void deleteAdjustmentAmount(String id) {
        adjustmentAmountRepository.delete(getAdjustmentAmount(id));
    }

    public AdjustmentAmountDto getAdjustmentAmountById(String id) {
        return converter.convert(getAdjustmentAmount(id));
    }

    public Set<AdjustmentAmountDto> getAllAdjustmentAmounts() {
        var adjustmentAmounts = new HashSet<>(adjustmentAmountRepository.findAll());

        BusinessRules.run(() -> rules.checkIfAdjustmentAmountListIsEmpty(adjustmentAmounts));

        return converter.convert(adjustmentAmounts);
    }

    protected AdjustmentAmount getAdjustmentAmount(String id) {
        var adjustmentAmount = adjustmentAmountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessMessages.ADJUSTMENT_AMOUNT_NOT_FOUND));

        return adjustmentAmount;
    }
}
