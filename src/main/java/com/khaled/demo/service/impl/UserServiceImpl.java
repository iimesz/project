package com.khaled.demo.service.impl;

import com.khaled.demo.model.dto.UserRegistrationRequestDto;
import com.khaled.demo.model.dto.UserRegistrationResponseDto;
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
    public UserRegistrationResponseDto register(UserRegistrationRequestDto dto) {

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
        contact.setCountry(dto.getCountry());
        contact.setCity(dto.getCity());
        contact.setPhoneNumber(dto.getPhoneNumber());
        contact.setUser(user);

        user.setUserContact(contact);

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    private UserRegistrationResponseDto mapToResponse(User user) {
        UserRegistrationResponseDto dto = new UserRegistrationResponseDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setBirthDate(user.getBirthDate());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }
}
