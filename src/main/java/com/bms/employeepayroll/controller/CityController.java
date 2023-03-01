package com.bms.employeepayroll.controller;

import com.bms.employeepayroll.dto.CityDto;
import com.bms.employeepayroll.service.CityService;
import com.bms.employeepayroll.service.requests.CreateCityRequest;
import com.bms.employeepayroll.service.requests.UpdateCityRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    private final CityService service;

    public CityController(CityService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createCity(@RequestBody CreateCityRequest request) {
        service.createCity(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCity(@PathVariable String id, @RequestBody UpdateCityRequest request) {
        service.updateCity(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable String id) {
        service.deleteCity(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getCityById(@PathVariable String id) {
        return ResponseEntity.ok(service.getCityById(id));
    }

    @GetMapping
    public ResponseEntity<Set<CityDto>> getAllCities() {
        return ResponseEntity.ok(service.getAllCities());
    }
}
