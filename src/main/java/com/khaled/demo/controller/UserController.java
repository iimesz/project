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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.TreeSet;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody UserDto request) {

        UserResponseDto response = userService.register(request);


//        // all the logic in the service
//        if (response == null) {
//            return ResponseEntity
//                    .status(HttpStatus.CONFLICT)
//                    .body("Email already exists");
//        }

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

    // Adding the Exception handler
}