package com.example.EmpMS.exception;


import com.example.EmpMS.customException.EmployeeNotFoundExecption;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        return  modelAndView;
    }
    @ExceptionHandler(EmployeeNotFoundExecption.class)
    public ResponseEntity<Object> employeeNotFoundExecption(EmployeeNotFoundExecption ex) {
        Map<String,Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
        body.put("status", HttpStatus.NOT_FOUND);
        body.put("timestamp", LocalDateTime.now());

        return  new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
