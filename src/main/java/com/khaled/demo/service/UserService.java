package com.khaled.demo.service;

import com.khaled.demo.model.dto.request.LoginRequestDto;
import com.khaled.demo.model.dto.respone.LoginResponseDto;
import com.khaled.demo.model.dto.request.UserDto;
import com.khaled.demo.model.dto.respone.UserResponseDto;

public interface UserService {

    UserResponseDto register(UserDto dto);

    LoginResponseDto login(LoginRequestDto loginRequest);
}
