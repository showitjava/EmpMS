package com.example.EmpMS;

import com.example.EmpMS.entity.Employee;
import com.example.EmpMS.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmpMsApplicationTests {


    private  EmployeeService  employeeService;


    @Autowired
    public EmployeeService setEmployeeService(EmployeeService employeeService)
    {
         this.employeeService =employeeService;
         return this.employeeService;
    }

	@Test
	void contextLoads() {
	}

    @Test
    public void test01() {
        String fristName = "Java";
        Assertions.assertEquals("Java", fristName);
    }
    @Test
    public void getEmployeeByidTest() {
        int id =1;
        Employee emp =employeeService.getEmpById(id);
        //expected and actual
        System.out.println(emp.toString());
        Assertions.assertEquals("Nitya",emp.getFirstName());
    }

    /*
    @Test
    public void test02() {
        String fristName = "Java1";
        Assertions.assertEquals("Java", fristName);
    }

    @Test
    public void test03() {
        int value =25;
        Assertions.assertEquals(5, Math.sqrt(value));

    }*/



}
