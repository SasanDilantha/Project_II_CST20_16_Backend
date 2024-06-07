package com.pms.user_server.controllers;

import com.pms.user_server.dto.UserLoginRequest;
import com.pms.user_server.dto.UserRequest;
import com.pms.user_server.dto.UserResponse;
import com.pms.user_server.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    // inject the service object
    private final UserService service;

    // register user
    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody @Valid UserRequest request) {
        return ResponseEntity.ok(service.addUser(request));
    }

    // Login user
    @GetMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody @Valid UserLoginRequest request) {
        return ResponseEntity.ok(service.isValidUser(request));
    }

    // view all users
    @GetMapping("/view_all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    // view all record of user
    @GetMapping("/{user-id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("user-id") Integer userId) {
        return ResponseEntity.ok(service.getUserByID(userId));
    }

}
