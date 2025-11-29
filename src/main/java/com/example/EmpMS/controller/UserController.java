package com.example.EmpMS.controller;

import com.example.EmpMS.dto.LoginRequest;
import com.example.EmpMS.dto.LoginResponse;
import com.example.EmpMS.dto.UserDto;
import com.example.EmpMS.entity.User;
import com.example.EmpMS.security.JwtUtil;
import com.example.EmpMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    public UserController(UserService userService, AuthenticationManager authenticationManager,JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
       this.jwtUtil = jwtUtil;
    }


    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {

        User user =userService.registerUser(userDto);
        return ResponseEntity.ok(user);

    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse>  loginUser(@RequestBody LoginRequest  loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        if(authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String role = userDetails.getAuthorities().iterator().next().toString();
            String token = jwtUtil.generateToken(userDetails.getUsername(),role);
            return ResponseEntity.ok(new LoginResponse(token));
        }
        else {
            return ResponseEntity.ok(new LoginResponse("Login failed"));
        }

    }
}
