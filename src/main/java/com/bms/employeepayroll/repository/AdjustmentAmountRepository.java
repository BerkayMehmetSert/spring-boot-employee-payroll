package com.bms.employeepayroll.repository;

import com.bms.employeepayroll.model.AdjustmentAmount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdjustmentAmountRepository extends JpaRepository<AdjustmentAmount, String> {
}