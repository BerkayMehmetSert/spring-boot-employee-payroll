package com.bms.employeepayroll.repository;

import com.bms.employeepayroll.model.WorkingAdjustment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkingAdjustmentRepository extends JpaRepository<WorkingAdjustment, String> {
}