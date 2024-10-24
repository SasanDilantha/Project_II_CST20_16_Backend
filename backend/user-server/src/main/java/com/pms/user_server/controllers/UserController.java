package com.pms.user_server.controllers;

import com.pms.user_server.dto.*;
import com.pms.user_server.dto.ui.UserUpadeRequest;
import com.pms.user_server.dto.ui.users.ManagerVetDetails;
import com.pms.user_server.dto.ui.users.UserUiResponse;
import com.pms.user_server.model.User;
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

    @GetMapping("/")
    public String home() {
        return "Welcome to User Server";
    }

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

    // view user details for UI
    @GetMapping("/all/ui")
    public ResponseEntity<List<UserUiResponse> > getAllUsersForUI() {
        return ResponseEntity.ok(service.getAllUsersForUI());
    }

    // get all managers
    @GetMapping("/managers")
    public ResponseEntity<List<ManagerVetDetails>> getAllManagers() {
        return ResponseEntity.ok(service.getAllManagers());
    }

    // get manager details for UI
    @GetMapping("/managers/{farm-id}")
    public ResponseEntity<ManagerVetDetails> getManagerDetails(@PathVariable("farm-id") Integer farmId){
        return  ResponseEntity.ok(service.getManagerDetails(farmId));
    }

    // get all vets
    @GetMapping("/vets")
    public ResponseEntity<List<ManagerVetDetails>> getAllVets() {
        return ResponseEntity.ok(service.getAllVets());
    }

    // get manager details for UI
    @GetMapping("/vets/{farm-id}")
    public ResponseEntity<ManagerVetDetails> getVetDetails(@PathVariable("farm-id") Integer farmId){
        return  ResponseEntity.ok(service.getVetDetails(farmId));
    }


    // update user
    @PutMapping("/update/user/details")
    public ResponseEntity<String> updateUserDetails(@RequestBody @Valid UserUpadeRequest userRequest){
        return  ResponseEntity.ok(service.updateUserDetails(userRequest));
    }


    // create or update salary
    @PutMapping("/salary")
    public ResponseEntity<Integer> updateOrSetSalary(@RequestBody @Valid UserSalaryRequest request) {
        return ResponseEntity.ok(service.updateOrSetSalary(request));
    }

    // count users by farm id
    @GetMapping("/count/{farm-id}")
    public ResponseEntity<Integer> countUsersByFarmId(@PathVariable("farm-id") Integer farmId) {
        return ResponseEntity.ok(service.countUsersByFarmId(farmId));
    }

    // check passwordIsCorrect
    @PostMapping("/check/password")
    public ResponseEntity<Boolean> passwordIsCorrect(@RequestBody CheckPassword request) {
        return ResponseEntity.ok(service.passwordIsCorrect(request.userId(), request.password()));
    }

    // Update User Endpoint
    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Integer userId, @RequestBody User updatedUser) {
        User user = service.updateUser(userId, updatedUser);
        return ResponseEntity.ok(user);
    }

    // Delete User Endpoint
    @DeleteMapping("/del/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        service.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

}
