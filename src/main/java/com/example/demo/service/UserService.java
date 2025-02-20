package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    public String createUser(User user);
    public String updateUser(User user);
    public String deleteUser(String userId);
    public User getUserDetails(String userId);
    public List<User> getAllUsers();
}
