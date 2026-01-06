package com.khaled.demo.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequestDto {

    @Valid
    @NotNull(message = "User information is required")
    private UserDto user;

    @Valid
    @NotNull(message = "Contact information is required")
    private UserContactDto contact;
}

