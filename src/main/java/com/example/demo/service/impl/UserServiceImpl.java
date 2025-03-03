package com.example.demo.service.impl;

import com.example.demo.dto.LoginRequest;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        UUID userId = UUID.randomUUID();
        user.setUserId(userId.toString());
        String hashedPassword = passwordEncoder.encode(user.getPasswordHash());
        user.setPasswordHash(hashedPassword);
        user.setStatus(User.UserStatus.ACTIVE);
        return userRepository.save(user);
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

    @Override
    public Optional<User> loginUser(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), user.get().getPasswordHash())) {
            return user;
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
