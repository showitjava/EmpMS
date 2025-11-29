package com.example.EmpMS.service;

import com.example.EmpMS.dto.UserDto;
import com.example.EmpMS.entity.User;
import com.example.EmpMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public User registerUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole() !=null ? userDto.getRole() : "USER");

        return  userRepository.save(user);
    }
}
