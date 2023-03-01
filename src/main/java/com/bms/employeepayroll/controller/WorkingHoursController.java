package com.bms.employeepayroll.controller;

import com.bms.employeepayroll.dto.WorkingHoursDto;
import com.bms.employeepayroll.service.WorkingHoursService;
import com.bms.employeepayroll.service.requests.CreateWorkingHoursRequest;
import com.bms.employeepayroll.service.requests.UpdateWorkingHoursRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/working-hours")
public class WorkingHoursController {
    private final WorkingHoursService workingHoursService;

    public WorkingHoursController(WorkingHoursService workingHoursService) {
        this.workingHoursService = workingHoursService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createWorkingHours(@RequestBody CreateWorkingHoursRequest request) {
        workingHoursService.createWorkingHours(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateWorkingHours(@PathVariable String id,
                                                   @RequestBody UpdateWorkingHoursRequest request) {
        workingHoursService.updateWorkingHours(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkingHours(@PathVariable String id) {
        workingHoursService.deleteWorkingHours(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkingHoursDto> getWorkingHoursById(@PathVariable String id) {
        return ResponseEntity.ok(workingHoursService.getWorkingHoursById(id));
    }

    @GetMapping
    public ResponseEntity<Set<WorkingHoursDto>> getAllWorkingHours() {
        return ResponseEntity.ok(workingHoursService.getAllWorkingHours());
    }
}
