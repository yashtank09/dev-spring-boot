package org.codecommando.cruddemo.dao;

import org.codecommando.cruddemo.entity.Student;

import java.util.List;

public interface StudentDao {
    void save(Student student);
    Student findById(Integer id);
    List<Student> findAll();
    List<Student> findByLastName(String lastName);
    void update(Student student);
    void delete(int studentId);
    int deleteAll();
}
