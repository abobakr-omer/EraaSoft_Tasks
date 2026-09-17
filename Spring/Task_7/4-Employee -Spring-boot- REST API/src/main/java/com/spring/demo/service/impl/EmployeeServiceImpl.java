package com.spring.demo.service.impl;

import com.spring.demo.model.Employee;
import com.spring.demo.repo.EmployeeRepo;
import com.spring.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.demo.exception.ValidationException;
import com.spring.demo.exception.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public List<Employee> getEmployeesByIds(List<Long> ids) {
        validateIds(ids);
        return requireEmployees(ids);
    }

    @Override
    public Employee saveEmployee(Employee employee) {

        validateNewEmployee(employee);

        return employeeRepo.save(employee);
    }

    @Override
    @Transactional
    public List<Employee> saveListOfEmployees(List<Employee> employees) {
        validateList(employees);
        employees.forEach(this::validateNewEmployee);
        return employeeRepo.saveAll(employees);
    }

    @Override
    @Transactional
    public Employee updateEmployee(Employee employee) {

        validateUpdateEmployee(employee);
        Employee existingEmployee = employeeRepo.findById(employee.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found: " + employee.getId()));

        existingEmployee.setName(employee.getName());
        existingEmployee.setAge(employee.getAge());
        existingEmployee.setPhoneNumber(employee.getPhoneNumber());

        return employeeRepo.save(existingEmployee);
    }

    @Override
    @Transactional
    public List<Employee> updateListOfEmployee(List<Employee> employees) {
        validateList(employees);
        Set<Long> requestedIds = new HashSet<>();
        for (Employee employee : employees) {
            validateUpdateEmployee(employee);
            if (!requestedIds.add(employee.getId())) {
                throw new ValidationException("Duplicate employee ID: " + employee.getId());
            }
        }
        List<Employee> existingEmployees = requireEmployees(employees.stream().map(Employee::getId).toList());

        Map<Long , Employee> inComingEmployees = employees.stream()
                .collect(Collectors.toMap(Employee::getId, employee -> employee));


        existingEmployees.forEach(existingEmployee -> {
            Employee updatedEmployee = inComingEmployees.get(existingEmployee.getId());

            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setAge(updatedEmployee.getAge());
            existingEmployee.setPhoneNumber(updatedEmployee.getPhoneNumber());
        });


        return employeeRepo.saveAll(existingEmployees);
    }

    @Override
    @Transactional
    public void deleteEmployeeById(Long id) {
        validateId(id);
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found: " + id));
        employeeRepo.delete(employee);
    }

    @Override
    public void deleteAllEmployee() {
        employeeRepo.deleteAll();
    }

    @Override
    @Transactional
    public void deleteEmployeeByIds(List<Long> ids) {
        validateIds(ids);
        employeeRepo.deleteAll(requireEmployees(ids));
    }

    // 1. Native SQL
    @Override
    public List<Employee> searchByNameNative(String name) {
        validateName(name);
        return employeeRepo.searchByNameNative(name);
    }


    // 2. JPQL
    @Override
    public List<Employee> searchByNameJPQL(String name) {
        validateName(name);
        return employeeRepo.searchByNameNonNative(name);
    }


    // 3. Derived Query
    @Override
    public List<Employee> searchByNameDerived(String name) {
        validateName(name);
        return employeeRepo.findByNameContainingIgnoreCase(name);
    }

    private void validateNewEmployee(Employee employee) {
        validateEmployee(employee);
        if (employee.getId() != null) {
            throw new ValidationException("A new employee must not have an ID");
        }
    }

    private void validateUpdateEmployee(Employee employee) {
        validateEmployee(employee);
        validateId(employee.getId());
    }

    private void validateEmployee(Employee employee) {
        if (employee == null) {
            throw new ValidationException("Employee is required");
        }
        validateName(employee.getName());
        if (employee.getAge() == null || employee.getAge() <= 0) {
            throw new ValidationException("Employee age is required and must be positive");
        }
        String phone = employee.getPhoneNumber();
        if (phone == null || phone.length() > 255 || !phone.matches("[+]?[0-9][0-9 ()-]*")) {
            throw new ValidationException("Phone number is required; use digits with optional +, spaces, parentheses, or hyphens");
        }
    }

    private void validateName(String name) {
        if (name == null || name.isBlank() || name.length() > 255) {
            throw new ValidationException("Employee name is required and must not exceed 255 characters");
        }
    }

    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new ValidationException("Employee ID must be positive");
        }
    }

    private void validateList(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            throw new ValidationException("Employee list must not be empty");
        }
    }

    private void validateIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new ValidationException("Employee ID list must not be empty");
        }
        Set<Long> unique = new HashSet<>();
        for (Long id : ids) {
            validateId(id);
            if (!unique.add(id)) {
                throw new ValidationException("Duplicate employee ID: " + id);
            }
        }
    }

    private List<Employee> requireEmployees(List<Long> ids) {
        List<Employee> employees = employeeRepo.findAllById(ids);
        Set<Long> missing = new HashSet<>(ids);
        employees.forEach(employee -> missing.remove(employee.getId()));
        if (!missing.isEmpty()) {
            throw new ResourceNotFoundException("Employees not found: " + missing);
        }
        return employees;
    }
}
