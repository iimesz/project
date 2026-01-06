package com.khaled.demo.service;

import com.khaled.demo.model.dto.UserRegistrationRequestDto;
import com.khaled.demo.model.dto.UserRegistrationResponseDto;

public interface UserService {

    UserRegistrationResponseDto register(UserRegistrationRequestDto dto);
}
