package com.khaled.demo.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResetPasswordRequest {

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
    private String oldPassword;


    @NotBlank(message = "New password is required")
    @Size(min = 8, max = 64, message = "New password must be between 8 and 64 characters")
        private String newPassword;

    @NotBlank(message = "Confirm password is required")
    @Size(min = 8, max = 64, message = "Confirm password must be between 8 and 64 characters")
    private String confirmPassword;


}
