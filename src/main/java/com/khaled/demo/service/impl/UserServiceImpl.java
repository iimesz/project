package com.khaled.demo.service.impl;

import com.khaled.demo.exception.customExceptions.DeleteUserException;
import com.khaled.demo.exception.customExceptions.JwtAuthenticationException;
import com.khaled.demo.exception.customExceptions.UpdateUserException;
import com.khaled.demo.exception.customExceptions.UserNotFoundException;
import com.khaled.demo.mapper.UserMapper;
import com.khaled.demo.model.dto.request.LoginRequestDto;
import com.khaled.demo.model.dto.respone.LoginResponseDto;
import com.khaled.demo.model.dto.request.UserDto;
import com.khaled.demo.model.dto.respone.UserInfoDto;
import com.khaled.demo.model.dto.respone.UserResponseDto;
import com.khaled.demo.model.entity.User;
import com.khaled.demo.repository.UserRepository;
import com.khaled.demo.security.JwtTokenProvider;
import com.khaled.demo.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public UserResponseDto register(UserDto dto) {

        String normalizedEmail = dto.getEmail().trim().toLowerCase();

        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new UpdateUserException("Email already exists");
        }

        User user = userMapper.toEntity(dto);
        user.setEmail(normalizedEmail);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequest) {

        String normalizedEmail = loginRequest.getEmail().trim().toLowerCase();

        User user = userRepository.findByEmail(normalizedEmail)
                .orElseThrow(() ->
                        new UserNotFoundException("Invalid email or password")
                );

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new UpdateUserException("Invalid email or password");
        }

        String accessToken = jwtTokenProvider.generateAccessToken(user.getEmail());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getEmail());

        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        return new LoginResponseDto(
                accessToken,
                refreshToken,
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    @Override
    public UserInfoDto getUserById(Long id, String authenticatedEmail) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id: " + id)
                );

        if (!user.getEmail().equals(authenticatedEmail)) {
            throw new UpdateUserException("Unauthorized: You can only access your own data");
        }

        return userMapper.toInfoDto(user);
    }



    @Override
    public UserResponseDto updateUser(Long id, UserDto dto, String authenticatedEmail) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id: " + id)
                );

        if (!user.getEmail().equals(authenticatedEmail)) {
            throw new UpdateUserException("Unauthorized: You can only modify your own data");
        }

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail().trim().toLowerCase());

        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        User updatedUser = userRepository.save(user);
        return userMapper.toResponse(updatedUser);
    }


    @Override
    public void deleteUser(Long id, String authenticatedEmail) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id: " + id)
                );

        if (!user.getEmail().equals(authenticatedEmail)) {
            throw new DeleteUserException("Unauthorized: You can only delete your own account");
        }

        userRepository.delete(user);
    }

    @Override
    public LoginResponseDto refreshToken(String refreshToken) {

        jwtTokenProvider.validateToken(refreshToken);

        String email = jwtTokenProvider.getEmailFromToken(refreshToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found")
                );

        if (!refreshToken.equals(user.getRefreshToken())) {
            throw new JwtAuthenticationException("Invalid refresh token");
        }

        String newAccessToken = jwtTokenProvider.generateAccessToken(user.getEmail());
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(user.getEmail());

        user.setRefreshToken(newRefreshToken);
        userRepository.save(user);

        return new LoginResponseDto(
                newAccessToken,
                newRefreshToken,
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );
    }
}
