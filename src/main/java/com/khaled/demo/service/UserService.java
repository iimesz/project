package com.khaled.demo.service;

import com.khaled.demo.model.dto.request.LoginRequestDto;
import com.khaled.demo.model.dto.respone.LoginResponseDto;
import com.khaled.demo.model.dto.request.UserDto;
import com.khaled.demo.model.dto.respone.UserInfoDto;
import com.khaled.demo.model.dto.respone.UserResponseDto;
import jakarta.validation.Valid;

public interface UserService {

    UserResponseDto register(UserDto dto);

    LoginResponseDto login(LoginRequestDto loginRequest);

    UserInfoDto getUserById(Long id, String name);

    UserResponseDto updateUser(Long id, @Valid UserDto request, String name);

    void deleteUser(Long id, String name);
}
