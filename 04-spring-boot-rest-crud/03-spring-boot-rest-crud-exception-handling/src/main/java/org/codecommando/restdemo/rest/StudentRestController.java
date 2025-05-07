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

    // Exception handler for StudentsNotFoundException
    // @ExceptionHandler allows handling specific exceptions at the controller level
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentsNotFoundException ex) {
        // Create a custom error response object
        StudentErrorResponse error = new StudentErrorResponse();

        // Set error details
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimestamp(String.valueOf(System.currentTimeMillis()));

        // Return a ResponseEntity with the error and HTTP status
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Generic exception handler for other exceptions
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception ex) {
        // Create a custom error response object
        StudentErrorResponse error = new StudentErrorResponse();

        // Set error details
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage()); // Optionally, customize the error message
        error.setTimestamp(String.valueOf(System.currentTimeMillis()));

        // Return a ResponseEntity with the error and HTTP status
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
