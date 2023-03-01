package com.bms.employeepayroll.repository;

import com.bms.employeepayroll.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {
    boolean existsByNameIgnoreCase(String name);
}