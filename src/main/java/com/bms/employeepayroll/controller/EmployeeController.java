package com.bms.employeepayroll.controller;

import com.bms.employeepayroll.dto.EmployeeDto;
import com.bms.employeepayroll.service.EmployeeService;
import com.bms.employeepayroll.service.requests.CreateEmployeeRequest;
import com.bms.employeepayroll.service.requests.UpdateEmployeeRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createEmployee(@RequestBody CreateEmployeeRequest request) {
        service.createEmployee(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable String id, @RequestBody UpdateEmployeeRequest request) {
        service.updateEmployee(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        service.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable String id) {
        return ResponseEntity.ok(service.getEmployeeById(id));
    }

    @GetMapping
    public ResponseEntity<Set<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(service.getAllEmployees());
    }
}
