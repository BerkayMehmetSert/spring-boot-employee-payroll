package com.bms.employeepayroll.repository;

import com.bms.employeepayroll.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    boolean existsByFirstNameAndLastName(String firstName, String lastName);

    boolean existsByEmail(String email);
}