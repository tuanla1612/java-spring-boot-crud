package com.example.demo.service.impl;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String createUser(User user) {
        userRepository.save(user);
        return "Successfully created user";
    }

    @Override
    public String updateUser(User user) {
        userRepository.save(user);
        return "Successfully updated user";
    }

    @Override
    public String deleteUser(String userId) {
        if (userRepository.findById(userId).isEmpty())
            throw new UserNotFoundException("Request user not found");
        userRepository.deleteById(userId);
        return "";
    }

    @Override
    public User getUserDetails(String userId) {
        if (userRepository.findById(userId).isEmpty())
            throw new UserNotFoundException("Request user not found");
        return userRepository.findById(userId).get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
