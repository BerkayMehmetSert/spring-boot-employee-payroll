package com.bms.employeepayroll.controller;

import com.bms.employeepayroll.dto.JobHistoryDto;
import com.bms.employeepayroll.service.JobHistoryService;
import com.bms.employeepayroll.service.requests.CreateJobHistoryRequest;
import com.bms.employeepayroll.service.requests.UpdateJobHistoryRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/job-histories")
public class JobHistoryController {
    private final JobHistoryService service;

    public JobHistoryController(JobHistoryService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createJobHistory(@RequestBody CreateJobHistoryRequest request) {
        service.createJobHistory(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateJobHistory(@PathVariable String id,
                                                 @RequestBody UpdateJobHistoryRequest request) {
        service.updateJobHistory(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobHistory(@PathVariable String id) {
        service.deleteJobHistory(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobHistoryDto> getJobHistoryById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getJobHistoryById(id));
    }

    @GetMapping
    public ResponseEntity<Set<JobHistoryDto>> getAllJobHistories() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllJobHistories());
    }
}
