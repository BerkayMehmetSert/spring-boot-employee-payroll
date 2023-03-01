package com.bms.employeepayroll.controller;

import com.bms.employeepayroll.dto.SalaryPaymentDto;
import com.bms.employeepayroll.service.SalaryPaymentService;
import com.bms.employeepayroll.service.requests.CreateSalaryPaymentRequest;
import com.bms.employeepayroll.service.requests.UpdateSalaryPaymentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/salary-payments")
public class SalaryPaymentController {
    private final SalaryPaymentService service;

    public SalaryPaymentController(SalaryPaymentService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createSalaryPayment(@RequestBody CreateSalaryPaymentRequest request) {
        service.createSalaryPayment(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSalaryPayment(@PathVariable String id,
                                                    @RequestBody UpdateSalaryPaymentRequest request) {
        service.updateSalaryPayment(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalaryPayment(@PathVariable String id) {
        service.deleteSalaryPayment(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaryPaymentDto> getSalaryPaymentById(@PathVariable String id) {
        return ResponseEntity.ok(service.getSalaryPaymentById(id));
    }

    @GetMapping
    public ResponseEntity<Set<SalaryPaymentDto>> getAllSalaryPayments() {
        return ResponseEntity.ok(service.getAllSalaryPayments());
    }
}
