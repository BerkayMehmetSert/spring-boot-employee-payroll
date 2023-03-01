package com.bms.employeepayroll.service.rules;

import com.bms.employeepayroll.core.exceptions.BusinessException;
import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.model.Department;
import com.bms.employeepayroll.repository.DepartmentRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DepartmentRules {
    private final DepartmentRepository repository;

    public DepartmentRules(DepartmentRepository repository) {
        this.repository = repository;
    }

    public void checkIfDepartmentExists(String name){
        var department = repository.existsByNameIgnoreCase(name);

        if(department){
            throw new BusinessException(BusinessMessages.DEPARTMENT_ALREADY_EXISTS);
        }
    }

    public void checkIfDepartmentListIsEmpty(Set<Department> departments){
        if(departments.isEmpty()){
            throw new NotFoundException(BusinessMessages.DEPARTMENT_LIST_IS_EMPTY);
        }
    }
}
