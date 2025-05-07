package org.codecommando.restdemo.exceptions;

public class StudentsNotFoundException extends RuntimeException {

    public StudentsNotFoundException(String message) {
        super(message);
    }

    public StudentsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentsNotFoundException(Throwable cause) {
        super(cause);
    }

}
