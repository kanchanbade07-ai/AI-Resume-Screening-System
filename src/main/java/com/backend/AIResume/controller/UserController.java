package com.backend.AIResume.controller;

import com.backend.AIResume.model.LoginRequest;
import com.backend.AIResume.model.User;
import com.backend.AIResume.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/test")
    public String test() {

        return "Secure API Working";
    }

    // REGISTER
    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {

        return userService.saveUser(user);
    }

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        return userService.login(request);
    }
}

