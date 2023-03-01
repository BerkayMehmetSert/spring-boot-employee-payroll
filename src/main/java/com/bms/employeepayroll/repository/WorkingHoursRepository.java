package com.bms.employeepayroll.repository;

import com.bms.employeepayroll.model.WorkingHours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkingHoursRepository extends JpaRepository<WorkingHours, String> {
}