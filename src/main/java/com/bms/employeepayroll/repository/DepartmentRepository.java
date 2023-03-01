package com.bms.employeepayroll.repository;

import com.bms.employeepayroll.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    boolean existsByNameIgnoreCase(String name);
}