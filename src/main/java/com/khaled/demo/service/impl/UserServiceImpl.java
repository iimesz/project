package com.khaled.demo.service.impl;

import com.khaled.demo.mapper.UserMapper;
import com.khaled.demo.model.dto.UserContactDto;
import com.khaled.demo.model.dto.UserDto;
import com.khaled.demo.model.dto.UserResponseDto;
import com.khaled.demo.model.entity.User;
import com.khaled.demo.model.entity.UserContact;
import com.khaled.demo.repository.UserRepository;
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

    @Override
    public UserResponseDto register(UserDto dto, UserContactDto udto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            return null; // handled in controller
        }

        // DTO → Entity
        User user = userMapper.toEntity(dto);

        // Security responsibility
        user.setPassword(passwordEncoder.encode(dto.getPassword()));


        UserContact userContact = userMapper.toEntity(udto) ;


        userContact.setUser(user);
        user.setUserContact(userContact);

        User savedUser = userRepository.save(user);

        // Entity → Response DTO
        return userMapper.toResponse(savedUser);
    }
}
