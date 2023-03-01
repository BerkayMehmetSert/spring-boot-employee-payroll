package com.bms.employeepayroll.repository;

import com.bms.employeepayroll.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenderRepository extends JpaRepository<Gender, String> {
    Optional<Gender> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
}