package org.codecommando.cruddemo.dao;

import org.codecommando.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// Exposes the repository as a REST resource with a custom path "members"
// @RepositoryRestResource(path = "members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // that's it ... not need to write any code
}
