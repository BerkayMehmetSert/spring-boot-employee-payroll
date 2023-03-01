package com.bms.employeepayroll.repository;

import com.bms.employeepayroll.model.DepartmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentHistoryRepository extends JpaRepository<DepartmentHistory, String> {
}