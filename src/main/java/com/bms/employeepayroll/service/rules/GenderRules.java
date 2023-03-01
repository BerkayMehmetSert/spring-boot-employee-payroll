package com.bms.employeepayroll.service.rules;

import com.bms.employeepayroll.core.exceptions.BusinessException;
import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.model.Gender;
import com.bms.employeepayroll.repository.GenderRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class GenderRules {
    private final GenderRepository repository;

    public GenderRules(GenderRepository repository) {
        this.repository = repository;
    }

    public void checkIfGenderExists(String name){
        var gender = repository.existsByNameIgnoreCase(name);

        if (gender) throw new BusinessException(BusinessMessages.GENDER_ALREADY_EXISTS);
    }

    public void checkIfGenderListIsEmpty(Set<Gender> genders){
        if (genders.isEmpty()) throw new NotFoundException(BusinessMessages.GENDER_LIST_EMPTY);
    }
}
