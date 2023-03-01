package com.bms.employeepayroll.service;

import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.core.utilities.services.rules.BusinessRules;
import com.bms.employeepayroll.core.utilities.services.rules.ValidationRules;
import com.bms.employeepayroll.dto.GenderDto;
import com.bms.employeepayroll.dto.converter.GenderDtoConverter;
import com.bms.employeepayroll.model.Gender;
import com.bms.employeepayroll.repository.GenderRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import com.bms.employeepayroll.service.constants.ValidationMessages;
import com.bms.employeepayroll.service.rules.GenderRules;
import com.bms.employeepayroll.service.validations.GenderValidation;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class GenderService {
    private final GenderRepository genderRepository;
    private final GenderDtoConverter converter;
    private final GenderRules rules;
    private final GenderValidation validation;

    public GenderService(GenderRepository genderRepository, GenderDtoConverter converter,
                         GenderRules rules, GenderValidation validation) {
        this.genderRepository = genderRepository;
        this.converter = converter;
        this.rules = rules;
        this.validation = validation;
    }

    public void createGender(String name) {
        BusinessRules.run(() -> rules.checkIfGenderExists(name));
        ValidationRules.run(() -> validation.notEmpty(name, ValidationMessages.NAME_CANNOT_BE_EMPTY));

        var gender = new Gender(name);

        genderRepository.save(gender);
    }

    public void updateGender(String id, String name) {
        var gender = getGender(id);

        ValidationRules.run(() -> validation.notEmpty(name, ValidationMessages.NAME_CANNOT_BE_EMPTY));

        var updatedGender = new Gender(
                gender.getId(),
                name,
                gender.getEmployees()
        );

        genderRepository.save(updatedGender);
    }

    public void deleteGender(String id) {
        genderRepository.delete(getGender(id));
    }

    public GenderDto getGenderByName(String name) {
        var gender = getGenderByGenderName(name);

        return converter.convert(gender);
    }

    public Set<GenderDto> getAllGenders() {
        var genders = new HashSet<>(genderRepository.findAll());

        BusinessRules.run(() -> rules.checkIfGenderListIsEmpty(genders));

        return converter.convert(genders);
    }

    protected Gender getGender(String id) {
        var gender = genderRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(BusinessMessages.GENDER_NOT_FOUND);
        });

        return gender;
    }

    private Gender getGenderByGenderName(String name) {
        var gender = genderRepository.findByNameIgnoreCase(name).orElseThrow(() -> {
            throw new NotFoundException(BusinessMessages.GENDER_NOT_FOUND);
        });

        return gender;
    }
}
