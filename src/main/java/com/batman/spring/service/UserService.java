package com.batman.spring.service;

import com.batman.spring.dto.*;
import com.batman.spring.entity.*;
import com.batman.spring.repository.*;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return userRepository.findByEmail(request.getEmail()).get();
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        return userRepository.save(user);
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
