package com.khaled.demo.controller;
import com.khaled.demo.model.dto.request.UserDto;
import com.khaled.demo.model.dto.respone.UserInfoDto;
import com.khaled.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody UserDto request) {
        userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserInfoDto>> getAllUsers() {
        List<UserInfoDto> users = (List<UserInfoDto>) userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    @PreAuthorize("@userSecurity.isOwner(#id, authentication) or hasRole('ADMIN')")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserInfoDto response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@userSecurity.isOwner(#id, authentication) or hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserDto request) {
        userService.updateUser(id, request);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@userSecurity.isOwner(#id, authentication) or hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
