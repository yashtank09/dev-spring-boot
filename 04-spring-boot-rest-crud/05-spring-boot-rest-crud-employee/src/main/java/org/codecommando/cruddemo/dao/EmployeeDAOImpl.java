package org.codecommando.cruddemo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.codecommando.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    // define fields for entity manager
    private EntityManager emr;

    // set up constructor injection
    @Autowired
    public EmployeeDAOImpl(EntityManager em) {
        emr = em;
    }

    @Override
    public List<Employee> findAll() {
        // create a query
        TypedQuery<Employee> query = emr.createQuery("FROM Employee", Employee.class);
        // execute query and get result list
        return query.getResultList();
    }

    @Override
    public Employee findById(int id) {
        return emr.find(Employee.class, id);
    }

    @Override
    public Employee save(Employee employee) {
        return emr.merge(employee);
    }

    @Override
    public void deleteById(int id) {
        Employee employee = emr.find(Employee.class, id);
        if (employee != null) {
            emr.remove(employee);
        }
    }
}
