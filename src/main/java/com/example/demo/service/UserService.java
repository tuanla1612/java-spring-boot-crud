package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User createUser(User user);
    public String updateUser(User user);
    public String deleteUser(String userId);
    public User getUserDetails(String userId);
    public List<User> getAllUsers();
    public Optional<User> loginUser(LoginRequest loginRequest);
    public Optional<User> findUserByEmail(String email);
}
