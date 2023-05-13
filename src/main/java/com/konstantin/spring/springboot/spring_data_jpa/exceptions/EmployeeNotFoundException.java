package com.konstantin.spring.springboot.spring_data_jpa.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(int id) {
        super("Not found Employee with id = " + id);
    }
}
