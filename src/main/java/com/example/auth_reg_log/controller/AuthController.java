package com.example.auth_reg_log.controller;

import com.example.auth_reg_log.model.User;
import com.example.auth_reg_log.service.UserService;
import com.example.auth_reg_log.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        User user = userService.register(username, password);
        return "User " + user.getUsername() + " registered successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userService.login(username, password);
        if (user.isPresent()) {
            String token = jwtUtil.generateToken(username);
            return "Login successful! Token: " + token;
        }
        return "Invalid username or password";
    }

}
