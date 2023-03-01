package com.bms.employeepayroll.service;

import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.core.utilities.services.rules.BusinessRules;
import com.bms.employeepayroll.core.utilities.services.rules.ValidationRules;
import com.bms.employeepayroll.dto.CityDto;
import com.bms.employeepayroll.dto.converter.CityDtoConverter;
import com.bms.employeepayroll.model.City;
import com.bms.employeepayroll.repository.CityRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import com.bms.employeepayroll.service.constants.ValidationMessages;
import com.bms.employeepayroll.service.requests.CreateCityRequest;
import com.bms.employeepayroll.service.requests.UpdateCityRequest;
import com.bms.employeepayroll.service.rules.CityRules;
import com.bms.employeepayroll.service.validations.CityValidation;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CityService {
    private final CityRepository cityRepository;
    private final CityDtoConverter converter;
    private final CountryService countryService;
    private final CityRules rules;
    private final CityValidation validation;

    public CityService(CityRepository cityRepository, CityDtoConverter converter,
                       CountryService countryService, CityRules rules,
                       CityValidation validation) {
        this.cityRepository = cityRepository;
        this.converter = converter;
        this.countryService = countryService;
        this.rules = rules;
        this.validation = validation;
    }

    public void createCity(CreateCityRequest request) {
        BusinessRules.run(() -> rules.checkIfCityExists(request.getName()));
        ValidationRules.run(() -> validation.notEmpty(request.getName(), ValidationMessages.NAME_CANNOT_BE_EMPTY));

        var city = new City(
                request.getName(),
                countryService.getCountry(request.getCountryId())
        );

        cityRepository.save(city);
    }

    public void updateCity(String id, UpdateCityRequest request) {
        var city = getCity(id);

        ValidationRules.run(() -> validation.notEmpty(request.getName(), ValidationMessages.NAME_CANNOT_BE_EMPTY));

        var updatedCity = new City(
                city.getId(),
                request.getName(),
                countryService.getCountry(request.getCountryId()),
                city.getEmployees()
        );

        cityRepository.save(updatedCity);
    }

    public void deleteCity(String id) {
        cityRepository.delete(getCity(id));
    }

    public CityDto getCityById(String id) {
        var city = getCity(id);

        return converter.convert(city);
    }

    public Set<CityDto> getAllCities() {
        var cities = new HashSet<>(cityRepository.findAll());

        BusinessRules.run(() -> rules.checkIfCityListIsEmpty(cities));

        return converter.convert(cities);
    }

    protected City getCity(String id) {
        var city = cityRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(BusinessMessages.CITY_NOT_FOUND);
                });

        return city;
    }
}
