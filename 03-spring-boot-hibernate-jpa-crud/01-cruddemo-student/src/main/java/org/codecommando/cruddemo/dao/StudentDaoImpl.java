package org.codecommando.cruddemo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.codecommando.cruddemo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public StudentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement save method
    @Override
    @Transactional // Insertion record is Transaction
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // crate query
        // TypedQuery<Student> query = entityManager.createQuery("FROM Student s", Student.class);
        // TypedQuery<Student> query = entityManager.createQuery("FROM Student s ORDER BY lastName ", Student.class); // ORDER BY ASC/DESC {Default: ASC}
        TypedQuery<Student> query = entityManager.createQuery("FROM Student s ORDER BY lastName ", Student.class); // ORDER BY ASC/DESC {Default: ASC}
        // return query results
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        // crate query
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE lastName=:theData", Student.class);

        // set query parameters
        query.setParameter("theData", lastName);

        // return query results
        return query.getResultList();
    }

    @Override
    @Transactional // because we are modifying record
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional // because we are modifying record
    public void delete(int studentId) {
        // retrieve the student
        Student theStudent = entityManager.find(Student.class, studentId);
        // delete the student
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional // because we are modifying record
    public int deleteAll() {
        return entityManager.createQuery("DELETE FROM Student ").executeUpdate();
    }
}
