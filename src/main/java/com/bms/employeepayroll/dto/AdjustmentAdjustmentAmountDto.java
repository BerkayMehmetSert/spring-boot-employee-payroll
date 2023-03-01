package com.bms.employeepayroll.dto;

public record AdjustmentAdjustmentAmountDto(
        String id,
        Double amount,
        Double percentage,
        String salaryPaymentId
) {
}
