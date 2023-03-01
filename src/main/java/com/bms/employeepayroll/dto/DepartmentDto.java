package com.bms.employeepayroll.dto;

import java.util.Set;

public record DepartmentDto(
        String id,
        String name,
        Set<DepartmentEmployeeDto> employees,
        Set<DepartmentDepartmentHistoryDto> departmentHistories
) {
}
