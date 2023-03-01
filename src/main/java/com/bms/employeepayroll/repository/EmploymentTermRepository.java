package com.bms.employeepayroll.repository;

import com.bms.employeepayroll.model.EmploymentTerm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmploymentTermRepository extends JpaRepository<EmploymentTerm, String> {
}