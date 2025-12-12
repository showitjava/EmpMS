package com.example.EmpMS.controller;

import com.example.EmpMS.entity.Employee;
import com.example.EmpMS.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name="Employee management ", description = " this tool for employee management")
public class EmployeeController {

    static List<Employee> employees = new  ArrayList<>();
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public String homepage() {

        return  "Welcome to Employee management system!!!";
    }
    // create Employee
    @PostMapping("/create")
    public Employee createEmployee(@RequestBody Employee employee)
    {
        //create a employee
        Employee emp =employeeService.createEmp(employee);
        //employees.add(employee);
        // save employee to database
        if(emp != null)
        {
            return  emp;
        }
        return new Employee();
    }
    @Operation(summary = "Get all Employee",description = "Fetch all the employees")
    @GetMapping("/employees")
    public List<Employee> getAllEmployees()
    {

        return employeeService.getAllEmp();
    }

    @GetMapping("/employees/{id}")
    public Employee getAllEmployees(@PathVariable Integer id)
    {

        return employeeService.getEmpById(id);
    }
    /// api/v1/employees
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee)
    {
        try {
            return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
        }catch(RuntimeException e)
        {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Integer id)
    {

             employeeService.deleteEmployee(id);
           return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("hello")
    public  String hello()
    {
        return "Hello from controller";
    }

    /*{
        "id": 4,
        "firstName": "Darshil",
        "lastName": "",
        "email": "Darshil2019@gmail.com",
        "phoneNumber": "1234567890",
        "address": "UP",
        "department": "CS",
        "salary": "10000"
    }*/
    @GetMapping("/testing")
    public String testing(){
        return "Deployed on AWS server !";
    }
    @GetMapping("/testing1")
    public String testing1(){
        return "Checking jenkins server";
    }


}
