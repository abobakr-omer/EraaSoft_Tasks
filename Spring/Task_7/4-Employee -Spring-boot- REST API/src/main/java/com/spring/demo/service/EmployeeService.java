package com.spring.demo.service;

import com.spring.demo.model.Employee;

import java.util.List;

public interface EmployeeService {


    List<Employee> getAllEmployees();

    List<Employee> getEmployeesByIds(List<Long> ids);

    Employee saveEmployee(Employee employee);

    List<Employee> saveListOfEmployees(List<Employee> employees);

    Employee updateEmployee(Employee employee);

    List<Employee> updateListOfEmployee(List<Employee> employees);

    void deleteEmployeeById(Long id);

    void deleteAllEmployee();

    void deleteEmployeeByIds(List<Long> ids);

    List<Employee> searchByNameNative(String name);

    List<Employee> searchByNameJPQL(String name);

    List<Employee> searchByNameDerived(String name);













}
