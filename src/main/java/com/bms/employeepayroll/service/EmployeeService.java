package com.bms.employeepayroll.service;

import com.bms.employeepayroll.core.utilities.helper.DateHelper;
import com.bms.employeepayroll.core.utilities.services.rules.BusinessRules;
import com.bms.employeepayroll.core.utilities.services.rules.ValidationRules;
import com.bms.employeepayroll.dto.EmployeeDto;
import com.bms.employeepayroll.dto.converter.EmployeeDtoConverter;
import com.bms.employeepayroll.model.Employee;
import com.bms.employeepayroll.repository.EmployeeRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import com.bms.employeepayroll.service.constants.ValidationMessages;
import com.bms.employeepayroll.service.requests.CreateEmployeeRequest;
import com.bms.employeepayroll.service.requests.UpdateEmployeeRequest;
import com.bms.employeepayroll.service.rules.EmployeeRules;
import com.bms.employeepayroll.service.validations.EmployeeValidation;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeDtoConverter converter;
    private final JobTitleService jobTitleService;
    private final DepartmentService departmentService;
    private final GenderService genderService;
    private final CityService cityService;
    private final EmployeeRules rules;
    private final EmployeeValidation validation;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeDtoConverter converter,
                           JobTitleService jobTitleService, DepartmentService departmentService,
                           GenderService genderService, CityService cityService,
                           EmployeeRules rules, EmployeeValidation validation) {
        this.employeeRepository = employeeRepository;
        this.converter = converter;
        this.jobTitleService = jobTitleService;
        this.departmentService = departmentService;
        this.genderService = genderService;
        this.cityService = cityService;
        this.rules = rules;
        this.validation = validation;
    }

    public void createEmployee(CreateEmployeeRequest request) {
        BusinessRules.run(() -> {
            rules.checkIfEmployeeExists(request.getFirstName(), request.getLastName());
            rules.checkIfEmployeeExistsByEmail(request.getEmail());
        });

        ValidationRules.run(() -> {
            validation.notEmpty(request.getFirstName(), ValidationMessages.FIRST_NAME_CANNOT_BE_EMPTY);
            validation.notEmpty(request.getLastName(), ValidationMessages.LAST_NAME_CANNOT_BE_EMPTY);
            validation.notEmpty(request.getAddress(), ValidationMessages.ADDRESS_CANNOT_BE_EMPTY);
            validation.notEmpty(request.getEmail(), ValidationMessages.EMAIL_CANNOT_BE_EMPTY);
            validation.email(request.getEmail(), ValidationMessages.EMAIL_REGEX, ValidationMessages.EMAIL_REGEX);
        });

        var employee = new Employee(
                request.getFirstName(),
                request.getLastName(),
                request.getDateOfBirth(),
                request.getAddress(),
                request.getEmail(),
                DateHelper.getCurrentDate(),
                jobTitleService.getJobTitle(request.getJobTitleId()),
                departmentService.getDepartment(request.getDepartmentId()),
                genderService.getGender(request.getGenderId()),
                cityService.getCity(request.getCityId())
        );

        employeeRepository.save(employee);
    }

    public void updateEmployee(String id, UpdateEmployeeRequest request) {
        var employee = getEmployee(id);

        ValidationRules.run(() -> {
            validation.notEmpty(request.getFirstName(), ValidationMessages.FIRST_NAME_CANNOT_BE_EMPTY);
            validation.notEmpty(request.getLastName(), ValidationMessages.LAST_NAME_CANNOT_BE_EMPTY);
            validation.notEmpty(request.getAddress(), ValidationMessages.ADDRESS_CANNOT_BE_EMPTY);
            validation.notEmpty(request.getEmail(), ValidationMessages.EMAIL_CANNOT_BE_EMPTY);
            validation.email(request.getEmail(), ValidationMessages.EMAIL_REGEX, ValidationMessages.EMAIL_REGEX);
        });

        var updatedEmployee = new Employee(
                employee.getId(),
                request.getFirstName(),
                request.getLastName(),
                request.getDateOfBirth(),
                request.getAddress(),
                request.getEmail(),
                employee.getEmploymentStart(),
                jobTitleService.getJobTitle(request.getJobTitleId()),
                departmentService.getDepartment(request.getDepartmentId()),
                genderService.getGender(request.getGenderId()),
                cityService.getCity(request.getCityId()),
                employee.getWorkingHours(),
                employee.getSalaryPayments(),
                employee.getEmploymentTerms(),
                employee.getJobHistories()
        );

        employeeRepository.save(updatedEmployee);
    }

    public void deleteEmployee(String id) {
        employeeRepository.delete(getEmployee(id));
    }

    public EmployeeDto getEmployeeById(String id) {
        var employee = getEmployee(id);

        return converter.convert(employee);
    }

    public Set<EmployeeDto> getAllEmployees() {
        var employees = new HashSet<>(employeeRepository.findAll());

        BusinessRules.run(() -> rules.checkIfEmployeeListIsEmpty(employees));

        return converter.convert(employees);
    }

    protected Employee getEmployee(String id) {
        var employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessMessages.EMPLOYEE_NOT_FOUND));

        return employee;
    }
}
