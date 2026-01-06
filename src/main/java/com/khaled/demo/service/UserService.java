package com.khaled.demo.service;

import com.khaled.demo.model.dto.UserContactDto;
import com.khaled.demo.model.dto.UserDto;
import com.khaled.demo.model.dto.UserResponseDto;

public interface UserService {

    UserResponseDto register(UserDto dto , UserContactDto udto);
}
