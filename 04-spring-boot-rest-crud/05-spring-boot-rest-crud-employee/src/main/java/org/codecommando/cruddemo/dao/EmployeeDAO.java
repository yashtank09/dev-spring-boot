package org.codecommando.cruddemo.dao;

import org.codecommando.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee id);
    void deleteById(int id);

}
