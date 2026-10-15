package com.eventhub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventhub.dto.EmployeeDto;
import com.eventhub.entity.Employee;
import com.eventhub.service.EmployeeService;

@RestController
@RequestMapping("/employees")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Add Employee
    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeDto employeeDto) {

        return new ResponseEntity<>(employeeService.addEmployee(employeeDto), HttpStatus.CREATED);
    }

    // Get All Employees
    @GetMapping
    public List<Employee> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    // Get Employee By Id
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {

        return employeeService.getEmployeeById(id);
    }

    // Update Employee
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Integer id,
                                                @RequestBody EmployeeDto employeeDto) {

        return new ResponseEntity<>(employeeService.updateEmployee(id, employeeDto), HttpStatus.OK);
    }

    // Delete Employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {

        return new ResponseEntity<>(employeeService.deleteEmployee(id), HttpStatus.OK);
    }
}