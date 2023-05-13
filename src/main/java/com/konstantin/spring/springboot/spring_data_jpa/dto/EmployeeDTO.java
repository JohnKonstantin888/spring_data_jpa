package com.konstantin.spring.springboot.spring_data_jpa.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EmployeeDTO {
    String name;
    String surname;
    String department;
    int salary;
}
