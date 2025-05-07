package org.codecommando.restdemo.rest;

import jakarta.annotation.PostConstruct;
import org.codecommando.restdemo.entity.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students;

    // define @PostConstruct method for loading students data

    @PostConstruct
    public void initStudentsList() {
        students = new ArrayList<>();
        students.add(new Student("Poornima", "Patel"));
        students.add(new Student("Jemica", "Potret"));
        students.add(new Student("Akash", "Rathod"));
    }

    // define endpoint for "/students" - return list of student
    // using simple endpoint
    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    // define endpoint or "/student/{studentId}" - return student at index
    // Using path variable
    @GetMapping("/student/{studentId}")
    public Student getStudentById(@PathVariable int studentId) {
        // Just index into list and it's simple
        return students.get(studentId);
    }

}
