package com.khaled.demo.service;
import java.util.List;

import com.khaled.demo.model.dto.request.LoginRequestDto;
import com.khaled.demo.model.dto.respone.LoginResponseDto;
import com.khaled.demo.model.dto.request.UserDto;
import com.khaled.demo.model.dto.respone.UserInfoDto;
import jakarta.validation.Valid;

public interface UserService {

    UserInfoDto register(UserDto dto);
    LoginResponseDto login(LoginRequestDto loginRequest);
    UserInfoDto getUserById(Long id, String name);
    UserInfoDto updateUser(Long id, @Valid UserDto request, String name);
    void deleteUser(Long id, String name);
    LoginResponseDto refreshToken(String refreshToken);
	List<UserInfoDto> getAllUsers();
    
}
