package com.bms.employeepayroll.controller;

import com.bms.employeepayroll.dto.DepartmentHistoryDto;
import com.bms.employeepayroll.service.DepartmentHistoryService;
import com.bms.employeepayroll.service.requests.CreateDepartmentHistoryRequest;
import com.bms.employeepayroll.service.requests.UpdateDepartmentHistoryRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/department-histories")
public class DepartmentHistoryController {
    private final DepartmentHistoryService service;

    public DepartmentHistoryController(DepartmentHistoryService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createDepartmentHistory(@RequestBody CreateDepartmentHistoryRequest request){
        service.createDepartmentHistory(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDepartmentHistory(@PathVariable String id,
                                                        @RequestBody UpdateDepartmentHistoryRequest request){
        service.updateDepartmentHistory(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartmentHistory(@PathVariable String id){
        service.deleteDepartmentHistory(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentHistoryDto> getDepartmentHistoryById(@PathVariable String id){
        return ResponseEntity.ok(service.getDepartmentHistoryById(id));
    }

    @GetMapping
    public ResponseEntity<Set<DepartmentHistoryDto>> getAllDepartmentHistories(){
        return ResponseEntity.ok(service.getAllDepartmentHistories());
    }
}
