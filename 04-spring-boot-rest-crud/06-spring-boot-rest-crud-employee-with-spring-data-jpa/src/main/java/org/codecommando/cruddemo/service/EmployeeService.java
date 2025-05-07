package org.codecommando.cruddemo.service;

import org.codecommando.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee id);
    void deleteById(int id);
}
