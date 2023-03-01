package com.bms.employeepayroll.repository;

import com.bms.employeepayroll.model.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTitleRepository extends JpaRepository<JobTitle, String> {
    boolean existsByTitleIgnoreCase(String title);
}