package com.eventhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventhub.dto.EmployeeDto;
import com.eventhub.entity.Employee;
import com.eventhub.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Add Employee
    public String addEmployee(EmployeeDto employeeDto) {

        Employee employee = new Employee();

        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setDesignation(employeeDto.getDesignation());
        employee.setPhone(employeeDto.getPhone());
        employee.setEmail(employeeDto.getEmail());
        employee.setSalary(employeeDto.getSalary());
        employee.setStatus(employeeDto.getStatus());

        employeeRepository.save(employee);

        return "Employee Added Successfully";
    }

    // Get All Employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get Employee By Id
    public Employee getEmployeeById(Integer id) {

        Optional<Employee> optional = employeeRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    // Update Employee
    public String updateEmployee(Integer id, EmployeeDto employeeDto) {

        Optional<Employee> optional = employeeRepository.findById(id);

        if (optional.isPresent()) {

            Employee employee = optional.get();

            employee.setEmployeeName(employeeDto.getEmployeeName());
            employee.setDesignation(employeeDto.getDesignation());
            employee.setPhone(employeeDto.getPhone());
            employee.setEmail(employeeDto.getEmail());
            employee.setSalary(employeeDto.getSalary());
            employee.setStatus(employeeDto.getStatus());

            employeeRepository.save(employee);

            return "Employee Updated Successfully";
        }

        return "Employee Not Found";
    }

    // Delete Employee
    public String deleteEmployee(Integer id) {

        if (employeeRepository.existsById(id)) {

            employeeRepository.deleteById(id);

            return "Employee Deleted Successfully";
        }

        return "Employee Not Found";
    }
}