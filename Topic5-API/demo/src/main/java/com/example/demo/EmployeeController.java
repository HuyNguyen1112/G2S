package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>>getAllEmployee() {
        List<EmployeeResponse> employees = employeeRepository.findAll().stream()
                .map(EmployeeMapper::toResponse)
                .toList();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employee) {
        Employee saveEmployee = employeeRepository.save(EmployeeMapper.toEntity(employee));
        return new ResponseEntity<>(EmployeeMapper.toResponse(saveEmployee), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable int id, @RequestBody Employee newData) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            employee.setFirstName(newData.getFirstName());
            employee.setLastName(newData.getLastName());
            employee.setBirthDate(newData.getBirthDate());
            employee.setSupervisorId(newData.getSupervisorId());
            return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.OK);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            employeeRepository.delete(employee);
            return "Deleted successfully!";
        }
        return "Employee not found!";
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeRepository.findById(id).orElse(null);
    }

}
