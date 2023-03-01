package com.bms.employeepayroll.controller;

import com.bms.employeepayroll.dto.JobTitleDto;
import com.bms.employeepayroll.service.JobTitleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/job-titles")
public class JobTitleController {
    private final JobTitleService service;

    public JobTitleController(JobTitleService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createJobTitle(@RequestParam String title) {
        service.createJobTitle(title);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateJobTitle(@PathVariable String id, @RequestParam String title) {
        service.updateJobTitle(id, title);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobTitle(@PathVariable String id) {
        service.deleteJobTitle(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobTitleDto> getJobTitleById(@PathVariable String id) {
        return ResponseEntity.ok(service.getJobTitleById(id));
    }

    @GetMapping
    public ResponseEntity<Set<JobTitleDto>> getAllJobTitles() {
        return ResponseEntity.ok(service.getAllJobTitles());
    }
}
