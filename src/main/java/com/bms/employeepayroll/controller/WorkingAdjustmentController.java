package com.bms.employeepayroll.controller;

import com.bms.employeepayroll.dto.WorkingAdjustmentDto;
import com.bms.employeepayroll.service.WorkingAdjustmentService;
import com.bms.employeepayroll.service.requests.CreateWorkingAdjustmentRequest;
import com.bms.employeepayroll.service.requests.UpdateWorkingAdjustmentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/working-adjustments")
public class WorkingAdjustmentController {
    private final WorkingAdjustmentService service;

    public WorkingAdjustmentController(WorkingAdjustmentService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createWorkingAdjustment(@RequestBody CreateWorkingAdjustmentRequest request) {
        service.createWorkingAdjustment(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateWorkingAdjustment(@PathVariable String id,
                                                        @RequestBody UpdateWorkingAdjustmentRequest request) {
        service.updateWorkingAdjustment(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkingAdjustment(@PathVariable String id) {
        service.deleteWorkingAdjustment(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkingAdjustmentDto> getWorkingAdjustmentById(@PathVariable String id) {
        return ResponseEntity.ok(service.getWorkingAdjustmentById(id));
    }

    @GetMapping
    public ResponseEntity<Set<WorkingAdjustmentDto>> getAllWorkingAdjustments() {
        return ResponseEntity.ok(service.getAllWorkingAdjustments());
    }
}
