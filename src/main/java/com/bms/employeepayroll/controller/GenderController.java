package com.bms.employeepayroll.controller;

import com.bms.employeepayroll.dto.GenderDto;
import com.bms.employeepayroll.service.GenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/genders")
public class GenderController {
    private final GenderService service;

    public GenderController(GenderService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createGender(@RequestParam String name) {
        service.createGender(name);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGender(@PathVariable String id, @RequestParam String name) {
        service.updateGender(id, name);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGender(@PathVariable String id) {
        service.deleteGender(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{name}")
    public ResponseEntity<GenderDto> getGenderByName(@PathVariable String name) {
        return ResponseEntity.ok(service.getGenderByName(name));
    }

    @GetMapping
    public ResponseEntity<Set<GenderDto>> getAllGenders() {
        return ResponseEntity.ok(service.getAllGenders());
    }
}
