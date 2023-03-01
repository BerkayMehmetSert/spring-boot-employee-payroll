package com.bms.employeepayroll.service.rules;

import com.bms.employeepayroll.core.exceptions.BusinessException;
import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.model.Country;
import com.bms.employeepayroll.repository.CountryRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CountryRules {
    private final CountryRepository repository;

    public CountryRules(CountryRepository repository) {
        this.repository = repository;
    }

    public void checkIfCountryExists(String name) {
        var country = repository.existsByNameIgnoreCase(name);

        if (country) {
            throw new BusinessException(BusinessMessages.COUNTRY_ALREADY_EXISTS);
        }
    }

    public void checkIfCountryListIsEmpty(Set<Country> countries) {
        if (countries.isEmpty()) {
            throw new NotFoundException(BusinessMessages.COUNTRY_LIST_EMPTY);
        }
    }
}
