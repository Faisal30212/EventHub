package com.eventhub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventhub.dto.LoginDto;
import com.eventhub.dto.LoginResponseDto;
import com.eventhub.dto.UserDto;
import com.eventhub.entity.User;
import com.eventhub.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    // ==========================================
    // REGISTER USER
    // ==========================================

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestBody UserDto userDto) {

        String message = userService.registerUser(userDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(message);
    }

    // ==========================================
    // LOGIN USER
    // ==========================================

    @PostMapping("/login")
    public LoginResponseDto loginUser(
            @RequestBody LoginDto loginDto) {

        return userService.loginUser(loginDto);
    }

    // ==========================
    // GET ALL USERS
    // ==========================

    @GetMapping
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    // ==========================================
    // GET USER BY ID
    // ==========================================

    @GetMapping("/{id}")
    public User getUserById(
            @PathVariable Integer id) {

        return userService.getUserById(id);
    }

    // ==========================================
    // UPDATE USER
    // ==========================================

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(
            @PathVariable Integer id,
            @RequestBody UserDto userDto) {

        String message = userService.updateUser(id, userDto);

        return ResponseEntity.ok(message);
    }

    // ==========================================
    // DELETE USER
    // ==========================================

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Integer id) {

        String message = userService.deleteUser(id);

        return ResponseEntity.ok(message);
    }
}