package com.bms.employeepayroll.controller;

import com.bms.employeepayroll.dto.AdjustmentDto;
import com.bms.employeepayroll.service.AdjustmentService;
import com.bms.employeepayroll.service.requests.CreateAdjustmentRequest;
import com.bms.employeepayroll.service.requests.UpdateAdjustmentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/adjustments")
public class AdjustmentController {
    private final AdjustmentService service;

    public AdjustmentController(AdjustmentService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createAdjustment(@RequestBody CreateAdjustmentRequest request) {
        service.createAdjustment(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAdjustment(@PathVariable String id,
                                                 @RequestBody UpdateAdjustmentRequest request) {
        service.updateAdjustment(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdjustment(@PathVariable String id) {
        service.deleteAdjustment(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdjustmentDto> getAdjustmentById(@PathVariable String id) {
        var adjustment = service.getAdjustmentById(id);
        return ResponseEntity.ok(adjustment);
    }

    @GetMapping
    public ResponseEntity<Set<AdjustmentDto>> getAllAdjustments() {
        var adjustments = service.getAllAdjustments();
        return ResponseEntity.ok(adjustments);
    }
}
