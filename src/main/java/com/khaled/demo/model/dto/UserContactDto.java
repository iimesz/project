package com.khaled.demo.model.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

import java.time.LocalDate;
@Data
public class UserContactDto {


    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "City is required")
    private String city;

    // 🇧🇭 Bahrain phone validation
    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^(3|6)\\d{7}$",
            message = "Invalid Bahrain phone number"
    )
    private String phoneNumber;
}
