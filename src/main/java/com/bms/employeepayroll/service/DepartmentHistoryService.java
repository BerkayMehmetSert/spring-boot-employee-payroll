package com.bms.employeepayroll.service;

import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.core.utilities.services.rules.BusinessRules;
import com.bms.employeepayroll.dto.DepartmentHistoryDto;
import com.bms.employeepayroll.dto.converter.DepartmentHistoryDtoConverter;
import com.bms.employeepayroll.model.DepartmentHistory;
import com.bms.employeepayroll.repository.DepartmentHistoryRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import com.bms.employeepayroll.service.requests.CreateDepartmentHistoryRequest;
import com.bms.employeepayroll.service.requests.UpdateDepartmentHistoryRequest;
import com.bms.employeepayroll.service.rules.DepartmentHistoryRules;
import com.bms.employeepayroll.service.validations.DepartmentHistoryValidation;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DepartmentHistoryService {
    private final DepartmentHistoryRepository departmentHistoryRepository;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final DepartmentHistoryDtoConverter converter;
    private final DepartmentHistoryRules rules;
    private final DepartmentHistoryValidation validation;

    public DepartmentHistoryService(DepartmentHistoryRepository departmentHistoryRepository,
                                    EmployeeService employeeService, DepartmentService departmentService,
                                    DepartmentHistoryDtoConverter converter,
                                    DepartmentHistoryRules rules, DepartmentHistoryValidation validation) {
        this.departmentHistoryRepository = departmentHistoryRepository;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.converter = converter;
        this.rules = rules;
        this.validation = validation;
    }

    public void createDepartmentHistory(CreateDepartmentHistoryRequest request) {
        BusinessRules.run(() -> {
            rules.checkIfDepartmentHistoryEndsBeforeStarts(request.getStartDate(), request.getEndDate());
        });

        var departmentHistory = new DepartmentHistory(
                request.getStartDate(),
                request.getEndDate(),
                employeeService.getEmployee(request.getEmployeeId()),
                departmentService.getDepartment(request.getDepartmentId())
        );

        departmentHistoryRepository.save(departmentHistory);
    }

    public void updateDepartmentHistory(String id, UpdateDepartmentHistoryRequest request) {
        BusinessRules.run(() -> {
            rules.checkIfDepartmentHistoryEndsBeforeStarts(request.getStartDate(), request.getEndDate());
        });

        var departmentHistory = getDepartmentHistory(id);

        var updatedDepartmentHistory = new DepartmentHistory(
                departmentHistory.getId(),
                request.getStartDate(),
                request.getEndDate(),
                employeeService.getEmployee(request.getEmployeeId()),
                departmentService.getDepartment(request.getDepartmentId())
        );

        departmentHistoryRepository.save(updatedDepartmentHistory);
    }

    public void deleteDepartmentHistory(String id) {
        departmentHistoryRepository.delete(getDepartmentHistory(id));
    }

    public DepartmentHistoryDto getDepartmentHistoryById(String id) {
        var departmentHistory = getDepartmentHistory(id);

        return converter.convert(departmentHistory);
    }

    public Set<DepartmentHistoryDto> getAllDepartmentHistories() {
        var departmentHistories = new HashSet<>(departmentHistoryRepository.findAll());

        BusinessRules.run(() -> rules.checkIfDepartmentHistoryListIsEmpty(departmentHistories));

        return converter.convert(departmentHistories);
    }

    protected DepartmentHistory getDepartmentHistory(String id) {
        var departmentHistory = departmentHistoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessMessages.DEPARTMENT_HISTORY_NOT_FOUND));

        return departmentHistory;
    }
}
