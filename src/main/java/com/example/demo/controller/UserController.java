package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/user")
public class UserController {
    User user;

    @GetMapping("{userId}")
    public User getUserDetails(String userId)
    {
        return user;
    }

    @PostMapping
    public String createUser(@RequestBody User user)
    {
        this.user = user;
        return "User created successfully";
    }

    @PutMapping
    public String updateUser(@RequestBody User user)
    {
        this.user = user;
        return "User updated successfully";
    }

    @DeleteMapping("{userId}")
    public String deleteUser(String userId)
    {
        this.user = null;
        return "User deleted successfully";
    }
}
