package com.bms.employeepayroll.repository;

import com.bms.employeepayroll.model.Adjustment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdjustmentRepository extends JpaRepository<Adjustment, String> {
    boolean existsByNameIgnoreCase(String name);
}