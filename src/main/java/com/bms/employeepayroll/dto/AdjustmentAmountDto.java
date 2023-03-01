package com.bms.employeepayroll.dto;

public record AdjustmentAmountDto(
        String id,
        Double amount,
        Double percentage,
        String adjustmentId,
        String salaryPaymentId
) {
}
