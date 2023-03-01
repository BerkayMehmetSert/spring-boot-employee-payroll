package com.bms.employeepayroll.repository;

import com.bms.employeepayroll.model.SalaryPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryPaymentRepository extends JpaRepository<SalaryPayment, String> {
}