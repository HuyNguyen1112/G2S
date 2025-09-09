package com.example.demo;

public class EmployeeMapper {
    public static EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse dto = new EmployeeResponse();

        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setBirthDate(employee.getBirthDate());
        dto.setSupervisorId(employee.getSupervisorId());

        return dto;
    }

    public static Employee toEntity(EmployeeRequest dto) {
        Employee employee = new Employee();

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setBirthDate(dto.getBirthDate());
        employee.setSupervisorId(dto.getSupervisorId());

        return employee;
    }
}
