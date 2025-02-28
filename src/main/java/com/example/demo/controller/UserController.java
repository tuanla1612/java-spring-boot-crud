package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.model.User;
import com.example.demo.response.ResponseHandler;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/user")
public class UserController {
    private final UserService userService;

    private final JwtTokenUtil jwtTokenUtil;

    public UserController(UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("{userId}")
    public ResponseEntity<Object> getUserDetails(@PathVariable("userId") String userId)
    {
        return ResponseHandler.responseBuilder("Request user detail is here", HttpStatus.OK, userService.getUserDetails(userId));
    }
    @GetMapping("/profile")
    public ResponseEntity<Object> getUserDetailsForProfile(@RequestHeader("Authorization") String authHeader)
    {
        String token = authHeader.substring(7);
        String email = jwtTokenUtil.getUsernameFromToken(token);
        Optional<User> user = userService.findUserByEmail(email);

        if (user.isPresent()) {
            return ResponseHandler.responseBuilder("Request user detail is here", HttpStatus.OK, user);
        } else {
            return ResponseHandler.responseBuilder("Wrong token!", HttpStatus.UNAUTHORIZED, Optional.empty());
        }
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

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User user)
    {
        return ResponseHandler.responseBuilder("User created successfully", HttpStatus.OK, userService.createUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userService.loginUser(loginRequest);
        if (user.isPresent()) {
            String token = jwtTokenUtil.generateToken(loginRequest.getEmail());
            return ResponseHandler.responseBuilder(token, HttpStatus.OK, user);
        } else {
            return ResponseHandler.responseBuilder("Invalid email or password", HttpStatus.BAD_REQUEST, Optional.empty());
        }
    }
}
