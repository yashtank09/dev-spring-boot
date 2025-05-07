package org.codecommando.restdemo.exceptions;

import org.codecommando.restdemo.response.StudentErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {
    // Add exception handling methods here if needed
    // For example, you can handle other exceptions globally for all controllers
    // using @ExceptionHandler annotations.

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
