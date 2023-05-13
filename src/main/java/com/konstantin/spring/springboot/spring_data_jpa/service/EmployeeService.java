package com.konstantin.spring.springboot.spring_data_jpa.service;

import com.konstantin.spring.springboot.spring_data_jpa.dao.EmployeeRepository;
import com.konstantin.spring.springboot.spring_data_jpa.dto.EmployeeDTO;
import com.konstantin.spring.springboot.spring_data_jpa.entity.Employee;
import com.konstantin.spring.springboot.spring_data_jpa.exceptions.EmployeeNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> findAllEmployees() {
        log.trace("Call findAllEmployees");
        return employeeRepository.findAll();
    }

    @Transactional
    public Employee createEmployee(String name, String surname, String department, int salary) {
        log.trace("Call createEmployee : name={}, surname={}, department={}, salary={}", name, surname, department, salary);
        return employeeRepository.save(new Employee()
                .setName(name)
                .setSurname(surname)
                .setDepartment(department)
                .setSalary(salary));
    }

    @Transactional
    public Employee updateEmployee(int id, EmployeeDTO employeeDTO) {
        log.trace("Call updateEmployee : id={}, employeeDTO={}", id, employeeDTO);
        Employee employee = findEmployeeById(id);
        return employee.setName(employeeDTO.getName())
                .setSurname(employeeDTO.getSurname())
                .setDepartment(employeeDTO.getDepartment())
                .setSalary(employeeDTO.getSalary());
    }

    public Employee findEmployeeById(int id) {
        log.trace("Call findEmployeeById : id={}", id);
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EmployeeNotFoundException(id);
    }

    @Transactional
    public void deleteEmployeeById(int id) {
        log.trace("Call deleteEmployeeById : id={}", id);
        Employee employee = findEmployeeById(id);
        employeeRepository.delete(employee);
    }

    public List<Employee> findAllEmployeesByName(String name) {
        log.trace("Call findAllEmployeesByName : name={}", name);
        return employeeRepository.findByNameIsContaining(name);
    }
}
