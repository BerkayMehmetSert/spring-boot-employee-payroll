package com.bms.employeepayroll.service.rules;

import com.bms.employeepayroll.core.exceptions.BusinessException;
import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.model.City;
import com.bms.employeepayroll.repository.CityRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CityRules {
    private final CityRepository repository;

    public CityRules(CityRepository repository) {
        this.repository = repository;
    }

    public void checkIfCityExists(String name) {
        var city = repository.existsByNameIgnoreCase(name);

        if (city) {
            throw new BusinessException(BusinessMessages.CITY_ALREADY_EXISTS);
        }
    }

    public void checkIfCityListIsEmpty(Set<City> cities) {
        if (cities.isEmpty()) {
            throw new NotFoundException(BusinessMessages.CITY_LIST_IS_EMPTY);
        }
    }
}
