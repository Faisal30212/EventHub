package com.eventhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventhub.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}