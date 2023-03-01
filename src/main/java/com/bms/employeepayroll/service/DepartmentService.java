package com.bms.employeepayroll.service;

import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.core.utilities.services.rules.BusinessRules;
import com.bms.employeepayroll.core.utilities.services.rules.ValidationRules;
import com.bms.employeepayroll.dto.DepartmentDto;
import com.bms.employeepayroll.dto.converter.DepartmentDtoConverter;
import com.bms.employeepayroll.model.Department;
import com.bms.employeepayroll.repository.DepartmentRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import com.bms.employeepayroll.service.constants.ValidationMessages;
import com.bms.employeepayroll.service.rules.DepartmentRules;
import com.bms.employeepayroll.service.validations.DepartmentValidation;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentDtoConverter converter;
    private final DepartmentRules rules;
    private final DepartmentValidation validation;

    public DepartmentService(DepartmentRepository departmentRepository, DepartmentDtoConverter converter,
                             DepartmentRules rules, DepartmentValidation validation) {
        this.departmentRepository = departmentRepository;
        this.converter = converter;
        this.rules = rules;
        this.validation = validation;
    }

    public void createDepartment(String name) {
        BusinessRules.run(() -> rules.checkIfDepartmentExists(name));
        ValidationRules.run(() -> validation.notEmpty(name, ValidationMessages.NAME_CANNOT_BE_EMPTY));

        var department = new Department(name);

        departmentRepository.save(department);
    }

    public void updateDepartment(String id, String name) {
        var department = getDepartment(id);

        ValidationRules.run(() -> validation.notEmpty(name, ValidationMessages.NAME_CANNOT_BE_EMPTY));

        var updatedDepartment = new Department(
                department.getId(),
                name,
                department.getEmployees(),
                department.getDepartmentHistories()
        );

        departmentRepository.save(updatedDepartment);
    }

    public void deleteDepartment(String id) {
        departmentRepository.delete(getDepartment(id));
    }

    public DepartmentDto getDepartmentById(String id) {
        var department = getDepartment(id);

        return converter.convert(department);
    }

    public Set<DepartmentDto> getAllDepartments() {
        var departments = new HashSet<>(departmentRepository.findAll());

        BusinessRules.run(() -> rules.checkIfDepartmentListIsEmpty(departments));

        return converter.convert(departments);
    }

    protected Department getDepartment(String id) {
        var department = departmentRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(BusinessMessages.DEPARTMENT_NOT_FOUND);
                });

        return department;
    }
}
