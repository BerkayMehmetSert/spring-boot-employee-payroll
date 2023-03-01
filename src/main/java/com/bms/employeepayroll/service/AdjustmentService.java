package com.bms.employeepayroll.service;

import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.core.utilities.services.rules.BusinessRules;
import com.bms.employeepayroll.core.utilities.services.rules.ValidationRules;
import com.bms.employeepayroll.dto.AdjustmentDto;
import com.bms.employeepayroll.dto.converter.AdjustmentDtoConverter;
import com.bms.employeepayroll.model.Adjustment;
import com.bms.employeepayroll.repository.AdjustmentRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import com.bms.employeepayroll.service.constants.ValidationMessages;
import com.bms.employeepayroll.service.requests.CreateAdjustmentRequest;
import com.bms.employeepayroll.service.requests.UpdateAdjustmentRequest;
import com.bms.employeepayroll.service.rules.AdjustmentRules;
import com.bms.employeepayroll.service.validations.AdjustmentValidation;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AdjustmentService {
    private final AdjustmentRepository adjustmentRepository;
    private final AdjustmentDtoConverter converter;
    private final AdjustmentValidation validation;
    private final AdjustmentRules rules;

    public AdjustmentService(AdjustmentRepository adjustmentRepository, AdjustmentDtoConverter converter,
                             AdjustmentValidation validation, AdjustmentRules rules) {
        this.adjustmentRepository = adjustmentRepository;
        this.converter = converter;
        this.validation = validation;
        this.rules = rules;
    }

    public void createAdjustment(CreateAdjustmentRequest request) {
        BusinessRules.run(() -> rules.checkIfAdjustmentExists(request.getName()));
        ValidationRules.run(() -> {
            validation.notEmpty(request.getName(), ValidationMessages.NAME_CANNOT_BE_EMPTY);
            validation.greaterThan(
                    request.getPercentage(),
                    0.0,
                    ValidationMessages.PERCENTAGE_GREATER_THAN_ZERO
            );
        });

        var adjustment = new Adjustment(
                request.getName(),
                request.getPercentage(),
                request.isWorkingHoursAdjustment(),
                request.isOtherAdjustment()
        );

        adjustmentRepository.save(adjustment);
    }

    public void updateAdjustment(String id, UpdateAdjustmentRequest request) {
        var adjustment = getAdjustment(id);

        ValidationRules.run(() -> {
            validation.notEmpty(request.getName(), ValidationMessages.NAME_CANNOT_BE_EMPTY);
            validation.greaterThan(
                    request.getPercentage(),
                    0.0,
                    ValidationMessages.PERCENTAGE_GREATER_THAN_ZERO
            );
        });

        var updatedAdjustment = new Adjustment(
                adjustment.getId(),
                request.getName(),
                request.getPercentage(),
                request.isWorkingHoursAdjustment(),
                request.isOtherAdjustment(),
                adjustment.getWorkingAdjustments(),
                adjustment.getAdjustmentAmounts()
        );

        adjustmentRepository.save(updatedAdjustment);
    }

    public void deleteAdjustment(String id) {
        adjustmentRepository.delete(getAdjustment(id));
    }

    public AdjustmentDto getAdjustmentById(String id) {
        var adjustment = getAdjustment(id);

        return converter.convert(adjustment);
    }

    public Set<AdjustmentDto> getAllAdjustments() {
        var adjustments = new HashSet<>(adjustmentRepository.findAll());

        BusinessRules.run(() -> rules.checkIfAdjustmentListIsEmpty(adjustments));

        return converter.convert(adjustments);
    }

    protected Adjustment getAdjustment(String id) {
        var adjustment = adjustmentRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(BusinessMessages.ADJUSTMENT_NOT_FOUND);
                });

        return adjustment;
    }
}
