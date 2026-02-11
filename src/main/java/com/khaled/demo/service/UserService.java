package com.khaled.demo.service;
import java.util.List;

import com.khaled.demo.model.dto.request.LoginRequestDto;
import com.khaled.demo.model.dto.respone.LoginResponseDto;
import com.khaled.demo.model.dto.request.UserDto;
import com.khaled.demo.model.dto.respone.UserInfoDto;

public interface UserService {

    UserInfoDto register(UserDto dto);
    LoginResponseDto login(LoginRequestDto loginRequest);
    UserInfoDto getUserById(Long id);
    void updateUser(Long id, UserDto request);
    void deleteUser(Long id);
    LoginResponseDto refreshToken(String refreshToken);
	List<UserInfoDto> getAllUsers();
    
}
