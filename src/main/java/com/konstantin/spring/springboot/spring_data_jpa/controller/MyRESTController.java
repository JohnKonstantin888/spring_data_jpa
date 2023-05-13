package com.konstantin.spring.springboot.spring_data_jpa.controller;

import com.konstantin.spring.springboot.spring_data_jpa.dto.EmployeeDTO;
import com.konstantin.spring.springboot.spring_data_jpa.entity.Employee;
import com.konstantin.spring.springboot.spring_data_jpa.exceptions.NotValidParameterException;
import com.konstantin.spring.springboot.spring_data_jpa.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class MyRESTController {

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> findAllEmployees() {
        log.trace("Call findAllEmployees");
        return employeeService.findAllEmployees();
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable int id) {
        log.trace("Call getEmployee : id={}", id);
        if (id <= 0) {
            throw new NotValidParameterException(id);
        }
        return employeeService.findEmployeeById(id);
    }

    @PostMapping("/employee")
    public Employee createEmployee(@RequestParam String name,
                                   @RequestParam String surname,
                                   @RequestParam String department,
                                   @RequestParam int salary) {
        log.trace("Call createEmployee : name={}, surname={}, department={}, salary={}", name, surname, department, salary);
        return employeeService.createEmployee(name, surname, department, salary);
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable int id,
                                   @RequestBody EmployeeDTO employeeDTO) {
        log.trace("Call updateEmployee : id={}, employee={}", id, employeeDTO);
        return employeeService.updateEmployee(id, employeeDTO);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        log.trace("Call deleteEmployee : id={}", id);
        employeeService.deleteEmployeeById(id);
        return "Employee with ID = " + id + " was deleted.";
    }

    @GetMapping("/employee/{name}")
    public List<Employee> findAllEmployeesByName(@PathVariable String name) {
        log.trace("Call findAllEmployeesByName : name={}", name);
        return employeeService.findAllEmployeesByName(name);
    }
}
