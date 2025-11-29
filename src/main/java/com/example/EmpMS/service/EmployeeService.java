package com.example.EmpMS.service;


import com.example.EmpMS.customException.EmployeeNotFoundExecption;
import com.example.EmpMS.entity.Employee;
import com.example.EmpMS.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;


    public Employee createEmp(Employee employee) {



        return repository.save(employee);
    }

    public List<Employee> getAllEmp() {

        return  repository.findAll();
    }

    public Employee getEmpById(Integer id) {
        Optional<Employee> emp =repository.findById(id);

        return emp.get();
    }

    public Employee updateEmployee(Integer id, Employee employee) {

        Employee emp = repository.findById(id).orElseThrow(()->new RuntimeException("Employee not found"));
        emp.setFirstName(employee.getFirstName());
        emp.setLastName(employee.getLastName());
        emp.setEmail(employee.getEmail());
        emp.setAddress(employee.getAddress());
        emp.setPhoneNumber(employee.getPhoneNumber());
        emp.setDepartment(employee.getDepartment());
        emp.setSalary(employee.getSalary());
        return repository.save(emp);
    }

    public void deleteEmployee(Integer id) {
        Employee emp = repository.findById(id).orElseThrow(()->new EmployeeNotFoundExecption("Employee not found id: "+id));
        repository.delete(emp);
    }
}
