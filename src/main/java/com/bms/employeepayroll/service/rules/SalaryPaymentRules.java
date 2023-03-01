package com.bms.employeepayroll.service.rules;

import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.model.SalaryPayment;
import com.bms.employeepayroll.repository.SalaryPaymentRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SalaryPaymentRules {
    private final SalaryPaymentRepository repository;

    public SalaryPaymentRules(SalaryPaymentRepository repository) {
        this.repository = repository;
    }

    public void checkIfSalaryPaymentListIsEmpty(Set<SalaryPayment> salaryPayments) {
        if (salaryPayments.isEmpty()) {
            throw new NotFoundException(BusinessMessages.SALARY_PAYMENT_LIST_EMPTY);
        }
    }
}
