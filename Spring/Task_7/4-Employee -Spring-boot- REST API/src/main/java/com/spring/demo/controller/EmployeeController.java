package com.spring.demo.controller;

import com.spring.demo.model.Employee;
import com.spring.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/all-employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/all-employees-By-Ids/{ids}")
    public List<Employee> getAllEmployeesByIds(@PathVariable List<Long> ids){
        return employeeService.getEmployeesByIds(ids);
    }

    @PostMapping("/save-employee")
    public Employee saveEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @PostMapping("/save-employees")
    public List<Employee> saveEmployee(@RequestBody List<Employee> employee){
        return employeeService.saveListOfEmployees(employee);
    }

    @PutMapping("/update-employee")
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeService.updateEmployee(employee);
    }

    @PutMapping("/update-employees")
    public List<Employee> updateListOfEmployee(@RequestBody List<Employee> employee){
        return employeeService.updateListOfEmployee(employee);
    }

    @DeleteMapping("/delete-all-employees")
    public void deleteAllEmployees(){
        employeeService.deleteAllEmployee();
    }

    @DeleteMapping("/delete-employees-by-ids/{ids}")
    public void deleteEmployeesByIds(@PathVariable List<Long> ids){
        employeeService.deleteEmployeeByIds(ids);
    }

    @DeleteMapping("/delete-employee/{id}")
    public void deleteEmployeeById(@PathVariable Long id){
        employeeService.deleteEmployeeById(id);
    }

    @GetMapping("/search-by-derived/{name}")
    public List<Employee> searchByDerived(@PathVariable String name){
        return employeeService.searchByNameDerived(name);
    }

    @GetMapping("/search-by-native/{name}")
    public List<Employee> searchByNative(@PathVariable String name){
        return employeeService.searchByNameNative(name);
    }
    @GetMapping("/search-by-jpql/{name}")
    public List<Employee> searchByJpql(@PathVariable String name){
        return employeeService.searchByNameJPQL(name);
    }










}
