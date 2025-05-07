package org.codecommando.restdemo.rest;

import jakarta.annotation.PostConstruct;
import org.codecommando.restdemo.entity.Student;
import org.codecommando.restdemo.exceptions.StudentsNotFoundException;
import org.codecommando.restdemo.response.StudentErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    // List to hold student data
    private List<Student> students;

    // @PostConstruct is used to initialize data after the bean is created
    @PostConstruct
    public void initStudentsList() {
        students = new ArrayList<>();
        students.add(new Student("Poornima", "Patel"));
        students.add(new Student("Jemica", "Potret"));
        students.add(new Student("Akash", "Rathod"));
    }

    // Endpoint to return the list of all students
    // @GetMapping maps HTTP GET requests to this method
    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    // Endpoint to return a specific student by their ID
    // @PathVariable binds the {studentId} from the URL to the method parameter
    @GetMapping("/student/{studentId}")
    public Student getStudentById(@PathVariable int studentId) {

        // Check if the studentId is valid, otherwise throw a custom exception
        if ((studentId >= students.size() || (studentId < 0))) {
            throw new StudentsNotFoundException("Student not found - " + studentId);
        }

        return students.get(studentId);
    }
}
