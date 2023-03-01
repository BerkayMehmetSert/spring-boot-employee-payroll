package com.bms.employeepayroll.repository;

import com.bms.employeepayroll.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, String> {
    boolean existsByNameIgnoreCase(String name);
}