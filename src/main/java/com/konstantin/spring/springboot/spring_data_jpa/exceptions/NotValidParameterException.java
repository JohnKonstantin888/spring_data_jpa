package com.konstantin.spring.springboot.spring_data_jpa.exceptions;

public class NotValidParameterException extends RuntimeException {
    public NotValidParameterException(int id) {
        super("Not valid index. Index must be more zero. Your id = " + id);
    }
}
