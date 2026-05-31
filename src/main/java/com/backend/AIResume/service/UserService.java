package com.backend.AIResume.service;

import com.backend.AIResume.model.LoginRequest;
import com.backend.AIResume.model.User;
import com.backend.AIResume.repository.UserRepository;
import com.backend.AIResume.jwt.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    // REGISTER
    public User saveUser(User user) {

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return userRepository.save(user);
    }

    // LOGIN
    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail());

        if(user == null) {
            return "Email not found";
        }

        boolean check = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if(check) {

            return jwtUtil.generateToken(user.getEmail());
        }
        else {

            return "Invalid Password";
        }
    }
}




