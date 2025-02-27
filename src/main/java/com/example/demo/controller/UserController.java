package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.model.User;
import com.example.demo.response.ResponseHandler;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping( "/user")
public class UserController {
    private final UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{userId}")
    public ResponseEntity<Object> getUserDetails(@PathVariable("userId") String userId)
    {
        return ResponseHandler.responseBuilder("Request user detail is here", HttpStatus.OK, userService.getUserDetails(userId));
    }

    @GetMapping()
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @PostMapping
    public String createUser(@RequestBody User user)
    {
        userService.createUser(user);
        return "User created successfully";
    }

    @PutMapping
    public String updateUser(@RequestBody User user)
    {
        userService.updateUser(user);
        return "User updated successfully";
    }

    @DeleteMapping("{userId}")
    public String deleteUser(@PathVariable("userId") String userId)
    {
        userService.deleteUser(userId);
        return "User deleted successfully";
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody User user)
    {
        userService.createUser(user);
        return "User created successfully";
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        if (userService.loginUser(loginRequest)) {
            String token = jwtTokenUtil.generateToken(loginRequest.getEmail());
            return ResponseEntity.ok("Bearer " + token);
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}
