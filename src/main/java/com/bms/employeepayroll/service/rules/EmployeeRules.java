package com.bms.employeepayroll.service.rules;

import com.bms.employeepayroll.core.exceptions.BusinessException;
import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.model.Employee;
import com.bms.employeepayroll.repository.EmployeeRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class EmployeeRules {
    private final EmployeeRepository repository;

    public EmployeeRules(EmployeeRepository repository) {
        this.repository = repository;
    }

    public void checkIfEmployeeExists(String firstName, String lastName) {
        if (repository.existsByFirstNameAndLastName(firstName, lastName)) {
            throw new BusinessException(BusinessMessages.EMPLOYEE_ALREADY_EXISTS);
        }
    }

    public void checkIfEmployeeExistsByEmail(String email) {
        if (repository.existsByEmail(email)) {
            throw new BusinessException(BusinessMessages.EMPLOYEE_ALREADY_EXISTS);
        }
    }

    public void checkIfEmployeeListIsEmpty(Set<Employee> employees){
        if (employees.isEmpty()) throw new NotFoundException(BusinessMessages.EMPLOYEE_LIST_EMPTY);
    }
}
