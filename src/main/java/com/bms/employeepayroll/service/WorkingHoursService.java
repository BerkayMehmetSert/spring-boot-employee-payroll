package com.bms.employeepayroll.service;

import com.bms.employeepayroll.core.exceptions.NotFoundException;
import com.bms.employeepayroll.core.utilities.services.rules.BusinessRules;
import com.bms.employeepayroll.dto.WorkingHoursDto;
import com.bms.employeepayroll.dto.converter.WorkingHoursDtoConverter;
import com.bms.employeepayroll.model.WorkingHours;
import com.bms.employeepayroll.repository.WorkingHoursRepository;
import com.bms.employeepayroll.service.constants.BusinessMessages;
import com.bms.employeepayroll.service.requests.CreateWorkingHoursRequest;
import com.bms.employeepayroll.service.requests.UpdateWorkingHoursRequest;
import com.bms.employeepayroll.service.rules.WorkingHoursRules;
import com.bms.employeepayroll.service.validations.WorkingHoursValidation;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class WorkingHoursService {
    private final WorkingHoursRepository workingHoursRepository;
    private final EmployeeService employeeService;
    private final WorkingHoursDtoConverter converter;
    private final WorkingHoursRules rules;
    private final WorkingHoursValidation validation;

    public WorkingHoursService(WorkingHoursRepository workingHoursRepository,
                               EmployeeService employeeService, WorkingHoursDtoConverter converter,
                               WorkingHoursRules rules, WorkingHoursValidation validation) {
        this.workingHoursRepository = workingHoursRepository;
        this.employeeService = employeeService;
        this.converter = converter;
        this.rules = rules;
        this.validation = validation;
    }

    public void createWorkingHours(CreateWorkingHoursRequest request) {
        BusinessRules.run(() -> {
            rules.checkIfWorkingHoursEndsBeforeStarts(request.getStartTime(), request.getEndTime());
        });

        var workingHours = new WorkingHours(
                request.getStartTime(),
                request.getEndTime(),
                employeeService.getEmployee(request.getEmployeeId())
        );

        workingHoursRepository.save(workingHours);
    }

    public void updateWorkingHours(String id, UpdateWorkingHoursRequest request) {
        BusinessRules.run(() -> {
            rules.checkIfWorkingHoursEndsBeforeStarts(request.getStartTime(), request.getEndTime());
        });

        var workingHours = getWorkingHours(id);

        var updatedWorkingHours = new WorkingHours(
                workingHours.getId(),
                request.getStartTime(),
                request.getEndTime(),
                employeeService.getEmployee(request.getEmployeeId()),
                workingHours.getWorkingAdjustments()
        );

        workingHoursRepository.save(updatedWorkingHours);
    }

    public void deleteWorkingHours(String id) {
        workingHoursRepository.delete(getWorkingHours(id));
    }

    public WorkingHoursDto getWorkingHoursById(String id) {
        return converter.convert(getWorkingHours(id));
    }

    public Set<WorkingHoursDto> getAllWorkingHours() {
        var workingHours = new HashSet<>(workingHoursRepository.findAll());

        BusinessRules.run(() -> rules.checkIfWorkingHoursListIsEmpty(workingHours));

        return converter.convert(workingHours);
    }

    protected WorkingHours getWorkingHours(String id) {
        var workingHours = workingHoursRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessMessages.WORKING_HOURS_NOT_FOUND));

        return workingHours;
    }
}
