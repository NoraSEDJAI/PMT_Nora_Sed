package com.nora_s.pmt_backend.service;

import org.springframework.stereotype.Service;

import com.nora_s.pmt_backend.entity.User;
import com.nora_s.pmt_backend.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }
}
