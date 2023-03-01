package com.bms.employeepayroll.controller;

import com.bms.employeepayroll.dto.EmploymentTermDto;
import com.bms.employeepayroll.service.EmploymentTermService;
import com.bms.employeepayroll.service.requests.CreateEmploymentTermRequest;
import com.bms.employeepayroll.service.requests.UpdateEmploymentTermRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/employment-terms")
public class EmploymentTermController {
    private final EmploymentTermService service;

    public EmploymentTermController(EmploymentTermService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createEmploymentTerm(@RequestBody CreateEmploymentTermRequest request) {
        service.createEmploymentTerm(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmploymentTerm(@PathVariable String id,
                                                     @RequestBody UpdateEmploymentTermRequest request) {
        service.updateEmploymentTerm(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmploymentTerm(@PathVariable String id) {
        service.deleteEmploymentTerm(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmploymentTermDto> getEmploymentTermById(@PathVariable String id) {
        return ResponseEntity.ok(service.getEmploymentTermById(id));
    }

    @GetMapping
    public ResponseEntity<Set<EmploymentTermDto>> getAllEmploymentTerms() {
        return ResponseEntity.ok(service.getAllEmploymentTerms());
    }
}
