package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public ResponseEntity<?>getAllEmployee() {
        return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee newData) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            employee.setFirstName(newData.getFirstName());
            employee.setLastName(newData.getLastName());
            employee.setBirthDate(newData.getBirthDate());
            employee.setSupervisorId(newData.getSupervisorId());
            return employeeRepository.save(employee);
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
