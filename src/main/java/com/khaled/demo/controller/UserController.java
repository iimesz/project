package com.khaled.demo.controller;

import com.khaled.demo.model.dto.UserContactDto;
import com.khaled.demo.model.dto.UserDto;
import com.khaled.demo.model.dto.UserResponseDto;
import com.khaled.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody UserDto dto,
            @Valid @RequestBody UserContactDto contactDto) {

        UserResponseDto response = userService.register(dto, contactDto);

        if (response == null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Email already exists");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}