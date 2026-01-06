package com.khaled.demo.service.impl;

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
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserDto dto , UserContactDto udto ) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            return null; // handled in controller
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setBirthDate(dto.getBirthDate());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        UserContact contact = new UserContact();
        contact.setCountry(udto.getCountry());
        contact.setCity(udto.getCity());
        contact.setPhoneNumber(udto.getPhoneNumber());
        contact.setUser(user);

        user.setUserContact(contact);

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    private UserResponseDto mapToResponse(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setBirthDate(user.getBirthDate());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }
}
