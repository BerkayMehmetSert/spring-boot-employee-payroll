package com.bms.employeepayroll.controller;

import com.bms.employeepayroll.dto.AdjustmentAmountDto;
import com.bms.employeepayroll.service.AdjustmentAmountService;
import com.bms.employeepayroll.service.requests.CreateAdjustmentAmountRequest;
import com.bms.employeepayroll.service.requests.UpdateAdjustmentAmountRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/adjustment-amounts")
public class AdjustmentAmountController {
    private final AdjustmentAmountService service;

    public AdjustmentAmountController(AdjustmentAmountService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createAdjustmentAmount(@RequestBody CreateAdjustmentAmountRequest request) {
        service.createAdjustmentAmount(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAdjustmentAmount(@PathVariable String id,
                                                       @RequestBody UpdateAdjustmentAmountRequest request) {
        service.updateAdjustmentAmount(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdjustmentAmount(@PathVariable String id) {
        service.deleteAdjustmentAmount(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdjustmentAmountDto> getAdjustmentAmountById(@PathVariable String id) {
        return ResponseEntity.ok(service.getAdjustmentAmountById(id));
    }

    @GetMapping
    public ResponseEntity<Set<AdjustmentAmountDto>> getAllAdjustmentAmounts() {
        return ResponseEntity.ok(service.getAllAdjustmentAmounts());
    }
}
