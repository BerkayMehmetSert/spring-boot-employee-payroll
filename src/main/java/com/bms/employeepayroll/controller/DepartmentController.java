package com.bms.employeepayroll.controller;

import com.bms.employeepayroll.dto.DepartmentDto;
import com.bms.employeepayroll.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createDepartment(@RequestParam String name) {
        service.createDepartment(name);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDepartment(@PathVariable String id, @RequestParam String name) {
        service.updateDepartment(id, name);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable String id) {
        service.deleteDepartment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable String id) {
        return ResponseEntity.ok(service.getDepartmentById(id));
    }

    @GetMapping
    public ResponseEntity<Set<DepartmentDto>> getAllDepartments() {
        return ResponseEntity.ok(service.getAllDepartments());
    }
}
