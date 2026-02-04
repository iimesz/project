package com.khaled.demo.controller;

import com.khaled.demo.model.dto.request.LoginRequestDto;
import com.khaled.demo.model.dto.request.UserDto;
import com.khaled.demo.model.dto.respone.LoginResponseDto;
import com.khaled.demo.model.dto.respone.UserResponseDto;
import com.khaled.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody UserDto request) {

        UserResponseDto response = userService.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto loginRequest) {

        // same the above
            LoginResponseDto response = userService.login(loginRequest);
            return ResponseEntity.ok(response);
    }



    // Add endpoint for show the info for user {id} and put and delete
    // it must be authorized

    // Get User by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUserById(
            @PathVariable Long id,
            Authentication authentication) {

        UserResponseDto response = userService.getUserById(id, authentication.getName());
        return ResponseEntity.ok(response);
    }

    // Update User
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserDto request,
            Authentication authentication) {

        UserResponseDto response = userService.updateUser(id, request, authentication.getName());
        return ResponseEntity.ok(response);
    }

    // Delete User
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteUser(
            @PathVariable Long id,
            Authentication authentication) {

        userService.deleteUser(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}