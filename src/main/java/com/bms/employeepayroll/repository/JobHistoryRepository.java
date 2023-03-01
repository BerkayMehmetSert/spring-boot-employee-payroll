package com.bms.employeepayroll.repository;

import com.bms.employeepayroll.model.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobHistoryRepository extends JpaRepository<JobHistory, String> {
}