package com.bk.springsecurity.controller;


import com.bk.springsecurity.Dto.LoginDTO;
import com.bk.springsecurity.Dto.UserDTO;
import com.bk.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping(path = "/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.findAll();
        if (users != null && !users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/save")
    public String saveEmployee(@RequestBody UserDTO userDTO)
    {
        return userService.addEmployee(userDTO);
    }

    @PostMapping(path = "/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        boolean isAuthenticated = userService.authenticateUser(loginDTO.getEmail(), loginDTO.getPassword());

        if (isAuthenticated) {
            return "Login successful!";
        } else {
            return "Invalid credentials. Login failed.";
        }
    }

    @GetMapping(path = "users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Integer userId) {
        UserDTO user = userService.findById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
