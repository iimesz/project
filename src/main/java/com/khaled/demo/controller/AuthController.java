package com.khaled.demo.controller;

import com.khaled.demo.model.dto.request.LoginRequestDto;
import com.khaled.demo.model.dto.request.RefreshTokenRequestDto;
import com.khaled.demo.model.dto.request.ResetPasswordRequest;
import com.khaled.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(
            @Valid @RequestBody RefreshTokenRequestDto request) {
        return ResponseEntity.ok(userService.refreshToken(request.getRefreshToken())
        );
    }
    @PreAuthorize("@userSecurity.isOwner(#id, authentication) or hasRole('ADMIN')")
    @PutMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
            @RequestBody ResetPasswordRequest request) {
        userService.resetPassword(request);
        return ResponseEntity.ok("Password reset successful");
    }

}
