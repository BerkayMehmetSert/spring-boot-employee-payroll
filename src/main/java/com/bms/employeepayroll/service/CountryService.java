package com.bms.employeepayroll.service;

import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.core.utilities.services.rules.BusinessRules;
import com.bms.employeepayroll.core.utilities.services.rules.ValidationRules;
import com.bms.employeepayroll.dto.CountryDto;
import com.bms.employeepayroll.dto.converter.CountryDtoConverter;
import com.bms.employeepayroll.model.Country;
import com.bms.employeepayroll.repository.CountryRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import com.bms.employeepayroll.service.constants.ValidationMessages;
import com.bms.employeepayroll.service.rules.CountryRules;
import com.bms.employeepayroll.service.validations.CountryValidation;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CountryService {
    private final CountryRepository countryRepository;
    private final CountryDtoConverter converter;
    private final CountryRules rules;
    private final CountryValidation validation;

    public CountryService(CountryRepository countryRepository, CountryDtoConverter converter,
                          CountryRules rules, CountryValidation validation) {
        this.countryRepository = countryRepository;
        this.converter = converter;
        this.rules = rules;
        this.validation = validation;
    }

    public void createCountry(String name) {
        BusinessRules.run(() -> rules.checkIfCountryExists(name));
        ValidationRules.run(() -> validation.notEmpty(name, ValidationMessages.NAME_CANNOT_BE_EMPTY));

        var country = new Country(name);

        countryRepository.save(country);
    }

    public void updateCountry(String id, String name) {
        var country = getCountry(id);

        ValidationRules.run(() -> validation.notEmpty(name, ValidationMessages.NAME_CANNOT_BE_EMPTY));

        var updatedCountry = new Country(
                country.getId(),
                name,
                country.getCities()
        );

        countryRepository.save(updatedCountry);
    }

    public void deleteCountry(String id) {
        countryRepository.delete(getCountry(id));
    }

    public CountryDto getCountryById(String id) {
        var country = getCountry(id);

        return converter.convert(country);
    }

    public Set<CountryDto> getAllCountries() {
        var countries = new HashSet<>(countryRepository.findAll());

        BusinessRules.run(() -> rules.checkIfCountryListIsEmpty(countries));

        return converter.convert(countries);
    }

    protected Country getCountry(String id) {
        var country = countryRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(BusinessMessages.COUNTRY_NOT_FOUND);
                });

        return country;
    }
}
