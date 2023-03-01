package com.bms.employeepayroll.controller;

import com.bms.employeepayroll.dto.CountryDto;
import com.bms.employeepayroll.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createCountry(@RequestParam String name) {
        service.createCountry(name);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCountry(@PathVariable String id, @RequestParam String name) {
        service.updateCountry(id, name);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable String id) {
        service.deleteCountry(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> getCountryById(@PathVariable String id) {
        var country = service.getCountryById(id);

        return ResponseEntity.ok(country);
    }

    @GetMapping
    public ResponseEntity<Set<CountryDto>> getAllCountries() {
        var countries = service.getAllCountries();

        return ResponseEntity.ok(countries);
    }
}
